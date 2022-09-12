package com.inventory.prosta.bot.service.api;

import org.telegram.telegrambots.meta.api.objects.Chat;
import jooq.tables.pojos.ChatDb;

import java.util.List;

public interface ChatService {
    /**
     * Достать чаты по Id пользователя
     */
    List<ChatDb> getAccountChats(Long accountId);

    /**
     * Проверка наличия чата в БД
     */
    boolean checkChat(Long chatId);

    /**
     * Сохранить чат
     */
    void saveChat(Chat chat);

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
     * Является ли чат групповым
     */
    boolean isGroupChat(Long chatId);

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
     * Проверка наличия account_chat
     */
    boolean userExistOnChat(Long accountId, Long ChatId);

    /**
     * Проверка  акккаунта в телеграм чате
     */
    boolean userChatInfo(Long userId, Long chatId);



}
