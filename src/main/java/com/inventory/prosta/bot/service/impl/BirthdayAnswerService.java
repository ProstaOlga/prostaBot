package com.inventory.prosta.bot.service.impl;

import com.inventory.prosta.bot.Context.AnswerContext;
import com.inventory.prosta.bot.model.BirthdayAnswerEvent;
import com.inventory.prosta.bot.model.UpdateContext;
import com.inventory.prosta.bot.model.enums.ButtonEnum;
import com.inventory.prosta.bot.service.api.AccountService;
import com.inventory.prosta.bot.service.api.AnswerService;
import com.inventory.prosta.bot.service.api.MessageService;
import com.inventory.prosta.bot.telegram.TelegramBotContext;
import com.inventory.prosta.bot.telegram.handler.keyboard.InlineKeyboardBuilder;
import jooq.tables.pojos.Account;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CompletableFuture;

@Getter
@Setter
@Service
@RequiredArgsConstructor
public class BirthdayAnswerService implements AnswerService<BirthdayAnswerEvent> {

    private static final String DATE_REGEX = "^(0[1-9]|[12][0-9]|3[01]).(0[1-9]|1[012]).(19|20)\\d{2}$";
    private final AccountService accountService;
    private final UpdateContext updateContext;
    private final AnswerContext<BirthdayAnswerEvent> answerContext;
    private final MessageService messageService;
    private final TelegramBotContext telegramBotContext;
    private final InlineKeyboardBuilder inlineKeyboardBuilder;

    private final String ERROR_TEXT = "Дата рождения не сохранена.\n" +
            "Пожалуйста, проверте формат\n" +
            "\nПример:\n" +
            "07.04.1995";

    private final String SUCCESS_TEXT = "Дата рождения установлена\n" +
            "\nпользователь: %s" +
            "\nдата рождения: %s";

    public void createBirthdayAnswer(Account account) {
        var event = BirthdayAnswerEvent.builder()
                .createDate(LocalDateTime.now())
                .chatId(updateContext.getChatId())
                .userid(updateContext.getUpdate().getCallbackQuery().getFrom().getId())
                .account(account)
                .onSaveBirthday(accountService::updateAccount)
                .build();

        answerContext.store(event);
    }


    @Override
    public boolean eventIsAnswer(BirthdayAnswerEvent answerEvent) {
        boolean result = false;
        Update update = updateContext.getUpdate();

        if (update.hasMessage() && answerEvent != null) {
            return checkConditions(update, answerEvent);
        }

        return result;
    }

    @Override
    public void executeAnswerEvent(BirthdayAnswerEvent birthdayAnswerEvent) {
        String text = updateContext.getUpdate().getMessage().getText();
        LocalDate birthDate = getDateFromText(text);

        birthdayAnswerEvent.getAccount().setBirthday(birthDate);

        CompletableFuture.runAsync(birthdayAnswerEvent::execute)
                .thenRun(() -> answerContext.remove(birthdayAnswerEvent))
                .join();

        successMessage(birthdayAnswerEvent.getAccount().getUserName(), text);
    }

    private LocalDate getDateFromText(String text) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        return LocalDate.parse(text, formatter);
    }

    private void errorMessage() {
        messageService.sendMessageToChat(updateContext.getChatId(), ERROR_TEXT);
    }

    private void successMessage(String accountName, String date) {
        String message = String.format(SUCCESS_TEXT, accountName, date);

        SendMessage sendMessage = SendMessage.builder()
                .chatId(updateContext.getChatId())
                .text(message)
                .replyMarkup(inlineKeyboardBuilder.getSingleButtonKeyboard(ButtonEnum.TO_MAIN))
                .build();

        messageService.sendMessageToChat(sendMessage);
    }


    private boolean checkConditions(Update update, BirthdayAnswerEvent answerEvent) {
        return checkChatId(answerEvent)
                && checkUserId(update, answerEvent)
                && checkText(updateContext.getUpdate().getMessage().getText());
    }

    private boolean checkChatId(BirthdayAnswerEvent answerEvent) {
        return updateContext.getChatId().equals(answerEvent.getChatId());
    }

    private boolean checkUserId(Update update, BirthdayAnswerEvent answerEvent) {
        return update.getMessage()
                .getFrom()
                .getId()
                .equals(answerEvent.getUserid());
    }

    private boolean checkText(String text) {
        return text != null && text.matches(DATE_REGEX);
    }


}
