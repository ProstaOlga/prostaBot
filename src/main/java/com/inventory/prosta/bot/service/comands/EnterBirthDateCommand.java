package com.inventory.prosta.bot.service.comands;

import com.inventory.prosta.bot.Context.AnswerContext;
import com.inventory.prosta.bot.model.answer.BirthdayAnswerEvent;
import com.inventory.prosta.bot.model.UpdateContext;
import com.inventory.prosta.bot.model.enums.ButtonEnum;
import com.inventory.prosta.bot.service.api.AccountService;
import com.inventory.prosta.bot.service.impl.BirthdayAnswerService;
import com.inventory.prosta.bot.telegram.keyboard.InlineKeyboardBuilder;
import com.inventory.prosta.bot.util.ResourceBundleUtil;
import com.inventory.prosta.bot.util.TextParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import jooq.tables.pojos.Account;

@Service
@RequiredArgsConstructor
@Slf4j
public class EnterBirthDateCommand implements Command{

    private final UpdateContext updateContext;
    private final InlineKeyboardBuilder inlineKeyboardBuilder;
    private final BirthdayAnswerService birthdayAnswerService;
    private final AccountService accountService;
    private final TextParser textParser;
    private final AnswerContext<BirthdayAnswerEvent> answerContext;

    private final static String TEXT_INSTRUCTION = ResourceBundleUtil.getMessageText("settings.birthday.instruction");

    @Override
    public BotApiMethod<?> execute() {
        String command = textParser.parseDataText(updateContext.getUpdate().getCallbackQuery().getData());

        return command.equals(ButtonEnum.ENTER_USER_BIRTH_DATE.getCommand())
                ? enterBirthDate()
                : cancelBirthDate(Long.parseLong(textParser.getDataParams(updateContext.getUpdate().getCallbackQuery().getData())));
    }

    private BotApiMethod<?> enterBirthDate(){
        Long creatorId = updateContext.getUpdate().getCallbackQuery().getFrom().getId();
        birthdayAnswerService.createBirthdayAnswer(getAccountFromData());

        log.info("User {}, pressed command ENTER BIRTH DATE", updateContext.getUpdate().getCallbackQuery().getFrom().getUserName());

        return EditMessageText.builder()
                .messageId(updateContext.getMessageId())
                .chatId(updateContext.getChatId())
                .text(String.format(TEXT_INSTRUCTION, getAccountFromData().getUserName()))
                .replyMarkup(inlineKeyboardBuilder.getCancelAnswerEventButton(ButtonEnum.CANCEL_BIRTH_DATE_ENTER, creatorId))
                .build();
    }

    private BotApiMethod<?> cancelBirthDate(Long userIdKey){
        answerContext.remove(userIdKey);

        log.info("User {}, pressed command CANCEL BIRTH DATE", updateContext.getUpdate().getCallbackQuery().getFrom().getUserName());

        return  EditMessageText.builder()
                .messageId(updateContext.getMessageId())
                .chatId(updateContext.getChatId())
                .text("Настройки")
                .replyMarkup(inlineKeyboardBuilder.getSettingsKeyboard())
                .build();
    }

    private Account getAccountFromData(){
        var data = updateContext.getUpdate().getCallbackQuery().getData();
        var params = textParser.getDataParams(data);
        Long accountId = Long.parseLong(params);

        return accountService.getAccountById(accountId);
    }
}
