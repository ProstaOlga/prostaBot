package com.inventory.prosta.bot.service.api;

import org.telegram.telegrambots.meta.api.objects.User;

import java.util.List;
import jooq.tables.pojos.Account;

public interface AccountService {
    /**
     * Регистрация нового пользователя
     */
    void registerNewAccount(User user);

    /**
     * Достать пользователя по id
     */
    Account getAccountById(Long telegramId);

    /**
     * Изменить пользователя
     */
    void updateAccount(Account account);

    /**
     * Достать список пользователей с днем рождения в текущий день
     */
    List<Account> getAccountsWithBirthdayNow();

    /**
     * Присоединить пользователя к чату
     */
    void joinChat(Long accountId, Long chatId);

    /**
     * Удалить пользователя из чата
     */
    void leaveChat(Long accountId, Long chatId);

    List<Account> getChatAccounts(Long chatId);

}
