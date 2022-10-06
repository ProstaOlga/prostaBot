package com.inventory.prosta.bot.service.comands;

import com.inventory.prosta.bot.Context.AnswerContext;
import com.inventory.prosta.bot.model.UpdateContext;
import com.inventory.prosta.bot.model.enums.ButtonEnum;
import com.inventory.prosta.bot.service.api.AccountService;
import com.inventory.prosta.bot.service.impl.BirthdayAnswerService;
import com.inventory.prosta.bot.telegram.handler.keyboard.InlineKeyboardBuilder;
import com.inventory.prosta.bot.util.TextParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
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

    private final String TEXT = "Введите дату рождения в следующем формате:\n" +
            "\nдень.месяц.год\n" +
            "\nНапример: 07.04.1995";

    @Override
    public BotApiMethod<?> execute() {
        birthdayAnswerService.createBirthdayAnswer(getAccountFromData());

        return EditMessageText.builder()
                .messageId(updateContext.getMessageId())
                .chatId(updateContext.getChatId())
                .text(TEXT)
                .replyMarkup(inlineKeyboardBuilder.getSingleButtonKeyboard(ButtonEnum.CANCEL_SETTINGS))
                .build();
    }

    private Account getAccountFromData(){
        var data = updateContext.getUpdate().getCallbackQuery().getData();
        var params = textParser.getDataParams(data);
        Long accountId = Long.parseLong(params);

        return accountService.getAccountById(accountId);
    }
}
