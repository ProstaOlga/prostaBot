package com.inventory.prosta.bot.service.impl;

import com.inventory.prosta.bot.Context.AnswerContext;
import com.inventory.prosta.bot.model.answer.BirthdayAnswerEvent;
import com.inventory.prosta.bot.model.enums.ButtonEnum;
import com.inventory.prosta.bot.service.api.AccountService;
import com.inventory.prosta.bot.service.api.MessageService;
import com.inventory.prosta.bot.telegram.TelegramBotContext;
import com.inventory.prosta.bot.telegram.keyboard.InlineKeyboardBuilder;
import jooq.tables.pojos.Account;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@RequiredArgsConstructor
public class BirthdayAnswerService extends AbstractAnswerService<BirthdayAnswerEvent> {

    private static final String DATE_REGEX = "^(0[1-9]|[12][0-9]|3[01])\\.(0[1-9]|1[012])\\.(19|20)\\d{2}$";
    private final AccountService accountService;
    private final AnswerContext<BirthdayAnswerEvent> answerContext;
    private final MessageService messageService;
    private final TelegramBotContext telegramBotContext;
    private final InlineKeyboardBuilder inlineKeyboardBuilder;

    private final String ERROR_TEXT = "⚠️Дата рождения не сохранена.\n" +
            "Пожалуйста, проверте формат\n" +
            "\nПример:\n" +
            "26.04.1998\n" +
            "\nДля отмены нажмите на кнопку \"Отмена\"";

    private final String SUCCESS_TEXT = "Дата рождения установлена\n" +
            "\nпользователь: @%s" +
            "\nдата рождения: %s";

    public void createBirthdayAnswer(Account account) {
        Long creatorId = updateContext.getUpdate().getCallbackQuery().getFrom().getId();

        var event = BirthdayAnswerEvent.builder()
                .createDate(LocalDateTime.now())
                .chatId(updateContext.getChatId())
                .userid(creatorId)
                .messageId(updateContext.getMessageId())
                .account(account)
                .onSaveBirthday(accountService::updateAccount)
                .build();

        answerContext.store(creatorId, event);

        log.info("Birthday answer event created at {}.", LocalDateTime.now());
    }


    @Override
    public boolean eventIsAnswer(BirthdayAnswerEvent answerEvent) {
        boolean result = false;
        Update update = updateContext.getUpdate();

        if (update.hasMessage() && answerEvent != null) {
            result = conditionsCheck(update, answerEvent);
        }

        log.info("Message: \"{}\" from user id {} is BirthdayAnswerEvent: {}.", update.getMessage().getText(),
                update.getMessage().getFrom().getUserName(),
                result);

        return result;
    }

    @Override
    public void executeAnswerEvent(BirthdayAnswerEvent birthdayAnswerEvent) {
        String text = updateContext.getUpdate().getMessage().getText();
        LocalDate birthDate = getDateFromText(text);

        birthdayAnswerEvent.getAccount().setBirthday(birthDate);

        CompletableFuture.runAsync(birthdayAnswerEvent::execute)
                .thenRun(() -> answerContext.remove(birthdayAnswerEvent.getUserid()))
                .join();

        successMessage(birthdayAnswerEvent.getAccount().getUserName(), text);
    }

    private LocalDate getDateFromText(String text) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        return LocalDate.parse(text, formatter);
    }

    @Override
    public boolean chatIdCheck(BirthdayAnswerEvent answerEvent) {
        boolean result = updateContext.getChatId().equals(answerEvent.getChatId());
        log.info("Chat id check is: {}.", result);

        return result;
    }

    @Override
    public boolean UserIdCheck(Update update, BirthdayAnswerEvent answerEvent) {
        boolean result =update.getMessage()
                .getFrom()
                .getId()
                .equals(answerEvent.getUserid());

                log.info("User id check is: {}.", result);

        return result;
    }

    @Override
    public boolean logicCheck() {
        String text = updateContext.getUpdate().getMessage().getText();

        log.info("Logic check is: {}, message is: {}.", text != null && text.matches(DATE_REGEX), text);

        return text != null && text.matches(DATE_REGEX);
    }

    @Override
    protected void errorMessage(BirthdayAnswerEvent answerEvent) {

        SendMessage sendMessage = SendMessage.builder()
                .chatId(updateContext.getChatId())
                .text(ERROR_TEXT)
                .replyMarkup(inlineKeyboardBuilder.getCancelAnswerEventButton(ButtonEnum.CANCEL_BIRTH_DATE_ENTER, answerEvent.getUserid()))
                .build();

        messageService.sendMessageToChat(sendMessage);

        log.info("Message \"{}\" from User {} does not match the format.",
                updateContext.getUpdate().getMessage().getText(),
                updateContext.getUpdate().getMessage().getFrom().getUserName());
    }

    @Override
    protected void successMessage(String accountName, String date) {
        String message = String.format(SUCCESS_TEXT, accountName, date);

        SendMessage sendMessage = SendMessage.builder()
                .chatId(updateContext.getChatId())
                .text(message)
                .replyMarkup(inlineKeyboardBuilder.getSingleButtonKeyboard(ButtonEnum.TO_MAIN))
                .build();

        messageService.sendMessageToChat(sendMessage);

        log.info("Message \"{}\" from User {} match the format.",
                updateContext.getUpdate().getMessage().getText(),
                updateContext.getUpdate().getMessage().getFrom().getUserName());
    }


}
