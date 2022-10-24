package com.inventory.prosta.bot.service.api;

import jooq.tables.pojos.Account;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;

import java.time.LocalDate;
import java.util.List;

public interface AccountService {
    /**
     * Регистрация нового пользователя.
     *
     * @param user - класс TelegramBotAPI, представляющий пользователя или бота Telegram.
     */
    void registerNewAccount(User user);

    /**
     * Достать аккаунт из бызы данных по id.
     *
     * @param telegramId - id пользователя в Telegram.
     * @return Account - entity класс, представляющий пользователя телеграм-бота.
     */
    Account getAccountById(Long telegramId);

    /**
     * Изменить пользователя.
     *
     * @param account entity класс, представляющий пользователя телеграм-бота.
     */
    void updateAccount(Account account);

    /**
     * Достать список пользователей с днем рождения в текущий день.
     *
     * @param localDate - дата, с которой будет происходить сравнение даты рождения пользователя.
     * @return List<Account> - список account из базы данных, с днем и месяцем рождения,
     * совпадающими с переданной в аргументе датой.
     */
    List<Account> getAccountsWithBirthdayNow(LocalDate localDate);

    /**
     * Присоединить пользователя к чату. Создает в базе данных новую запись
     * AccountChat.
     *
     * @param user - класс TelegramBotAPI, представляющий пользователя или бота Telegram.
     * @param chat - класс TelegramBotAPI, представляющий чат.
     */
    void joinChat(User user, Chat chat);

    /**
     * Достать список аккаунтов, привязанных к данному чату.
     *
     * @param chatId - id Telegram-чата.
     * @return List<Account> - список account из базы данных,
     * которые привязанны к чату с id, переданным в аргументе.
     */
    List<Account> getChatAccounts(Long chatId);

}
