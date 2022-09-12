package com.inventory.prosta.bot.service.api;

import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;

import java.time.LocalDate;
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
    User getUserById(Long telegramId);

    /**
     * Сохранить пользователя
     */
    void saveUser(User user);

    /**
     * Сохранить дату рождения пользователя
     */
    void saveUserBirthday(Long userId, LocalDate date);

    /**
     * Изменить дату рождения пользователя
     */
    void updateUserBirthday(Long userId, LocalDate date);

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

}
