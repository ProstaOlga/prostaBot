package com.inventory.prosta.bot.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor
public enum ButtonNameEnum {
    NOTIFICATIONS("notifications", "Настроить уведомления"),
    HOLIDAY_CONGRATULATION_ON("holidaySwitchON", "Включить праздничные поздравления"),
    HOLIDAY_CONGRATULATION_OFF("holidaySwitchOFF", "Отключить праздничные поздравления"),
    BIRTHDAY_CONGRATULATION_ON("birthdaySwitchON", "Включить поздравления с Днем Рождения"),
    BIRTHDAY_CONGRATULATION_OFF("birthdaySwitchOFF", "Отключить поздравления с Днем Рождения"),
    DAILY_GREETING_ON("dailyGreetingSwitchON", "Включить ежедневные приветствия"),
    DAILY_GREETING_OF("dailyGreetingSwitchOFF", "Отключить ежедневные приветствия"),
    SET_DATE_OF_BIRTH("setDAteOfBirth", "Настроить дату рождения пользователя");


    private final String command;
    private final String name;

}
