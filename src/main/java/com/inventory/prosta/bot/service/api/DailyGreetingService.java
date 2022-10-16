package com.inventory.prosta.bot.service.api;

public interface DailyGreetingService {
    /**
     * Отправить утреннее приветствие в чаты.
     */
    void sendGoodMorning();

    /**
     * Отправить пожелание доброй ночи в чаты.
     */
    void sendGoodNight();
}
