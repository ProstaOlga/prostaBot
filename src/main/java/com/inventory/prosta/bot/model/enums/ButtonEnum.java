package com.inventory.prosta.bot.model.enums;

import com.inventory.prosta.bot.util.Symbols;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum ButtonEnum {
    SETTINGS("settings", "Настройки"),
    DATE_OF_BIRTH_SETTINGS("dAteOfBirth", "Настроить дату рождения"),
    ENTER_USER_BIRTH_DATE( "setUserBirth", ""),
    NOTIFICATION_SETTINGS("notificationSettings", "Настроить уведомления"),
    BACK_SETTINGS( "settings", Symbols.BACK_ARROW + "Назад"),
    CANCEL_BIRTH_DATE_ENTER("cancelBirthDateEnter", "Отмена"),
    HOLIDAY_CONGRATULATION_ENABLE("holidaySwitchON", Symbols.RED_CIRCLE_EMOJI + " Праздничные поздравления"),
    HOLIDAY_CONGRATULATION_DISABLE("holidaySwitchOFF", Symbols.GREEN_CIRCLE_EMOJI + " Праздничные поздравления"),
    BIRTHDAY_CONGRATULATION_ENABLE("birthdaySwitchON", Symbols.RED_CIRCLE_EMOJI + " Поздравления с Днем Рождения"),
    BIRTHDAY_CONGRATULATION_DISABLE("birthdaySwitchOFF", Symbols.GREEN_CIRCLE_EMOJI + " Поздравления с Днем Рождения"),
    DAILY_GREETING_ENABLE("dailyGreetingSwitchON", Symbols.RED_CIRCLE_EMOJI + " Ежедневные приветствия"),
    DAILY_GREETING_DISABLE("dailyGreetingSwitchOFF", Symbols.GREEN_CIRCLE_EMOJI + " Ежедневные приветствия"),
    CAT("getCat", "Хочу котика"),
    CAT_ANOTHER("getCat", "Хочу еще котика"),
    TO_MAIN("toMain", Symbols.BACK_ARROW + "На главную"),
    INFO( "info", "Информация"),
    BACK_INFO( "info", Symbols.BACK_ARROW + "назад"),
    INFO_WHAT_CAN_DO( "infoWhatCanDo", "Что умеет бот"),
    INFO_SETTINGS("infoSettings", "Информация о настройках бота"),
    ADMIN_MEDIA_SELECT_TYPE("selectMediaType", "Добавить изображения"),
    ADMIN_MEDIA_CREATE_ANSWER_EVENT("createMediaAnswer", "Загрузить изображение"),
    ADMIN_MEDIA_CANCEL_ANSWER_EVENT("cancelMediaAnswer", "Отмена"),
    TO_ADMIN("admin", Symbols.BACK_ARROW + "Назад");

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
