package com.inventory.prosta.bot.service.api;

import org.telegram.telegrambots.meta.api.objects.Chat;

public interface ChatService {
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

    boolean isBirthdayNoticeOn(Long chatId);

    boolean isHolidayNoticeOn(Long chatId);

    boolean isDailyNoticeOn(Long chatId);


}
