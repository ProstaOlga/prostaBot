package com.inventory.prosta.bot.model.enums;

import com.inventory.prosta.bot.service.comands.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.NoSuchElementException;

@Getter
@RequiredArgsConstructor
public enum CallbackQueryType {
    SETTINGS(ButtonEnum.SETTINGS, SettingsCommand.class),
    NOTIFICATION_SETTINGS(ButtonEnum.NOTIFICATION_SETTINGS, NotificationSettingsCommand.class),
    HOLIDAY_CONGRATULATION_ENABLE(ButtonEnum.HOLIDAY_CONGRATULATION_ENABLE, HolidaySwitchCommand.class),
    HOLIDAY_CONGRATULATION_DISABLE(ButtonEnum.HOLIDAY_CONGRATULATION_DISABLE, HolidaySwitchCommand.class),
    BIRTHDAY_CONGRATULATION_ENABLE(ButtonEnum.BIRTHDAY_CONGRATULATION_ENABLE, BirthdaySwitchCommand.class),
    BIRTHDAY_CONGRATULATION_DISABLE(ButtonEnum.BIRTHDAY_CONGRATULATION_DISABLE, BirthdaySwitchCommand.class),
    DAILY_GREETING_ENABLE(ButtonEnum.DAILY_GREETING_ENABLE, DailyGreetingSwitchCommand.class),
    DAILY_GREETING_DISABLE(ButtonEnum.DAILY_GREETING_DISABLE, DailyGreetingSwitchCommand.class),
    ENTER_DATE_OF_BIRTH(ButtonEnum.DATE_OF_BIRTH_SETTINGS, DateOfBirthCommand.class),
    INFO(ButtonEnum.INFO, InfoCommand.class),
    INFO_WHAT_CAN_DO(ButtonEnum.INFO_WHAT_CAN_DO, InfoCommand.class),
    INFO_SETTINGS(ButtonEnum.INFO_SETTINGS, InfoCommand.class),
    CAT(ButtonEnum.CAT, WannaCatCommand.class),
    SET_USER_BIRTH_DATE(ButtonEnum.ENTER_USER_BIRTH_DATE, EnterBirthDateCommand.class),
    TO_MAIN(ButtonEnum.TO_MAIN, MainPageCommand.class);


    private final ButtonEnum button;
    private final Class<? extends Command> clazz;

    public static Class<? extends Command> getCommandClass(String command){
        return Arrays.stream(CallbackQueryType.values())
                .filter(type -> type.getButton().getCommand().equals(command))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(String.format("Command: %s not found.", command)))
                .getClazz();
    }

    public String getCommandToString(){
        return this.getButton().getCommand();
    }

}
