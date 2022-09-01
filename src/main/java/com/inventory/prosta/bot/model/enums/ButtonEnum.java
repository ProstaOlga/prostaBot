package com.inventory.prosta.bot.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@RequiredArgsConstructor
public enum ButtonEnum {
    NOTIFICATIONS("notifications", "Настройки"),
    HOLIDAY_CONGRATULATION_ENABLE("holidaySwitchON", "Включить праздничные поздравления"),
    HOLIDAY_CONGRATULATION_DISABLE("holidaySwitchOFF", "Отключить праздничные поздравления"),
    BIRTHDAY_CONGRATULATION_ENABLE("birthdaySwitchON", "Включить поздравления с Днем Рождения"),
    BIRTHDAY_CONGRATULATION_DISABLE("birthdaySwitchOFF", "Отключить поздравления с Днем Рождения"),
    DAILY_GREETING_ENABLE("dailyGreetingSwitchON", "Включить ежедневные приветствия"),
    DAILY_GREETING_DISABLE("dailyGreetingSwitchOFF", "Отключить ежедневные приветствия"),
    SET_DATE_OF_BIRTH("setDAteOfBirth", "Настроить дату рождения пользователя"),
    INFO("info", "Информация"),
    CAT("getCat", "Хочу котика"),
    CAT_ANOTHER("getCat", "Хочу еще котика"),
    TO_MAIN("toMain", "На главную")
    ;


    private final String command;
    private final String name;

    public InlineKeyboardButton renderButton() {
        return InlineKeyboardButton.builder()
                .text(this.name)
                .callbackData(this.command)
                .build();
    }

}
