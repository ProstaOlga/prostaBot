package com.inventory.prosta.bot.service.comands;

import com.inventory.prosta.bot.Context.AnswerContext;
import com.inventory.prosta.bot.model.BirthdayAnswerEvent;
import com.inventory.prosta.bot.model.UpdateContext;
import com.inventory.prosta.bot.model.enums.ButtonEnum;
import com.inventory.prosta.bot.service.api.AccountService;
import com.inventory.prosta.bot.service.impl.BirthdayAnswerService;
import com.inventory.prosta.bot.telegram.handler.keyboard.InlineKeyboardBuilder;
import com.inventory.prosta.bot.util.TextParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import jooq.tables.pojos.Account;

@Service
@RequiredArgsConstructor
public class EnterBirthDateCommand implements Command{

    private final UpdateContext updateContext;
    private final InlineKeyboardBuilder inlineKeyboardBuilder;
    private final BirthdayAnswerService birthdayAnswerService;
    private final AccountService accountService;
    private final TextParser textParser;
    private final AnswerContext<BirthdayAnswerEvent> answerContext;

    private final String TEXT = "Введите дату рождения для @%s в следующем формате:\n" +
            "\nдень.месяц.год\n" +
            "Например: 23.05.1998\n";

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

        return EditMessageText.builder()
                .messageId(updateContext.getMessageId())
                .chatId(updateContext.getChatId())
                .text(String.format(TEXT, getAccountFromData().getUserName()))
                .replyMarkup(inlineKeyboardBuilder.getBirthDateCancelButton(ButtonEnum.CANCEL_BIRTH_DATE_ENTER, creatorId))
                .build();
    }

    private BotApiMethod<?> cancelBirthDate(Long userIdKey){
        answerContext.remove(userIdKey);

        return  EditMessageReplyMarkup.builder()
                .messageId(updateContext.getMessageId())
                .chatId(updateContext.getChatId())
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
