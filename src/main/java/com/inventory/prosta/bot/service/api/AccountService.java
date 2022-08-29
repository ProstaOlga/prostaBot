package com.inventory.prosta.bot.service.api;

import org.mapstruct.control.MappingControl;
import org.telegram.telegrambots.meta.api.objects.User;

import java.time.LocalDate;

public interface AccountService {
    /**
     * Аутентификация пользователя
     */
    boolean authenticate(User user);

    /**
     * Вернуть пользователя по id
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

}
