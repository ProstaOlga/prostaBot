package com.inventory.prosta.bot.service.api;

import org.telegram.telegrambots.meta.api.objects.Chat;
import jooq.tables.pojos.ChatDb;
import org.telegram.telegrambots.meta.api.objects.User;
import jooq.tables.pojos.Account;

import java.util.List;

public interface ChatService {
    /**
     * Достать чаты по Id пользователя
     */
    List<ChatDb> getAccountChats(Long accountId);

    /**
     * Включить уведомления праздников в чате
     */
    void onHolidayNotice(Long chatId);

    /**
     * Отключить уведомления праздников в чате
     */
    void offHolidayNotice(Long chatId);

    /**
     * Включить уведомления о днях рождениях в чате
     */
    void onBirthdayNotice(Long chatId);

    /**
     * Отключить уведомления о днях рождениях в чате
     */
    void offBirthdayNotice(Long chatId);

    /**
     * Включить ежедневные приветствия в чате
     */
    void onDailyNotice(Long chatId);

    /**
     * Отключить ежедневные приветствия в чате
     */
    void offDailyNotice(Long chatId);

    /**
     * Аутонтефикация и регистрация новых чатов
     */
    void registerNewChat(Chat chat);

    /**
     * Проверка включения уведомлений о днях рождения
     */
    boolean isBirthdayNoticeOn(Long chatId);

    /**
     * Проверка включения уведомлений о празниках
     */
    boolean isHolidayNoticeOn(Long chatId);

    /**
     * Проверка включения ежедневных приветствий
     */
    boolean isDailyNoticeOn(Long chatId);

    /**
     * Проверка наличия аккаунта в чате
     */
    boolean userExistOnChat(Long accountId, Long ChatId);
}
