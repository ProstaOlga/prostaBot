package com.inventory.prosta.bot.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum ButtonEnum {
    SETTINGS("settings", "Настройки"),
    HOLIDAY_CONGRATULATION_ENABLE("holidaySwitchON", "Включить праздничные поздравления"),
    HOLIDAY_CONGRATULATION_DISABLE("holidaySwitchOFF", "Отключить праздничные поздравления"),
    BIRTHDAY_CONGRATULATION_ENABLE("birthdaySwitchON", "Включить поздравления с Днем Рождения"),
    BIRTHDAY_CONGRATULATION_DISABLE("birthdaySwitchOFF", "Отключить поздравления с Днем Рождения"),
    DAILY_GREETING_ENABLE("dailyGreetingSwitchON", "Включить ежедневные приветствия"),
    DAILY_GREETING_DISABLE("dailyGreetingSwitchOFF", "Отключить ежедневные приветствия"),
    SET_DATE_OF_BIRTH("setDAteOfBirth", "Настроить дату рождения пользователя"),
    CAT("getCat", "Хочу котика"),
    CAT_ANOTHER("getCat", "Хочу еще котика"),
    TO_MAIN("toMain", "На главную"),
    INFO( "info", "Информация"),
    BACK_INFO( "info", "назад"),
    INFO_WHAT_CAN_DO( "infoWhatCanDo", "Что умеет бот"),
    INFO_SETTINGS("infoSettings", "Информация о настройках бота");

    private final String command;
    private final String text;


    public InlineKeyboardButton renderButton() {
        return InlineKeyboardButton.builder()
                .text(this.text)
                .callbackData(this.command)
                .build();
    }

    public static ButtonEnum getButtonEnumFromString(String string){
        return Arrays.stream(ButtonEnum.values())
                .filter(button -> button.getCommand().equals(string))
                .findFirst()
                .orElseThrow();
    }

}
