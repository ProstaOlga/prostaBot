package com.inventory.prosta.bot.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@RequiredArgsConstructor
public enum ButtonEnum {
    NOTIFICATIONS("notifications", "Настройки"),
    HOLIDAY_CONGRATULATION_ON("holidaySwitchON", "Включить праздничные поздравления"),
    HOLIDAY_CONGRATULATION_OFF("holidaySwitchOFF", "Отключить праздничные поздравления"),
    BIRTHDAY_CONGRATULATION_ON("birthdaySwitchON", "Включить поздравления с Днем Рождения"),
    BIRTHDAY_CONGRATULATION_OFF("birthdaySwitchOFF", "Отключить поздравления с Днем Рождения"),
    DAILY_GREETING_ON("dailyGreetingSwitchON", "Включить ежедневные приветствия"),
    DAILY_GREETING_OF("dailyGreetingSwitchOFF", "Отключить ежедневные приветствия"),
    SET_DATE_OF_BIRTH("setDAteOfBirth", "Настроить дату рождения пользователя"),
    INFO("info", "Информация");


    private final String command;
    private final String name;

    public InlineKeyboardButton renderButton() {
        return InlineKeyboardButton.builder()
                .text(this.name)
                .callbackData(this.command)
                .build();
    }

}
