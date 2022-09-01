package com.inventory.prosta.bot.model.enums;

import com.inventory.prosta.bot.service.comands.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum CallbackQueryType {
    NOTIFICATIONS(ButtonEnum.NOTIFICATIONS, NotificationsCommand.class),
    HOLIDAY_CONGRATULATION_ENABLE(ButtonEnum.HOLIDAY_CONGRATULATION_ENABLE, HolidaySwitchCommand.class),
    HOLIDAY_CONGRATULATION_DISABLE(ButtonEnum.HOLIDAY_CONGRATULATION_DISABLE, HolidaySwitchCommand.class),
    BIRTHDAY_CONGRATULATION_ENABLE(ButtonEnum.BIRTHDAY_CONGRATULATION_ENABLE, BirthdaySwitchCommand.class),
    BIRTHDAY_CONGRATULATION_DISABLE(ButtonEnum.BIRTHDAY_CONGRATULATION_DISABLE, BirthdaySwitchCommand.class),
    DAILY_GREETING_ENABLE(ButtonEnum.DAILY_GREETING_ENABLE, DailyGreetingSwitchCommand.class),
    DAILY_GREETING_DISABLE(ButtonEnum.DAILY_GREETING_DISABLE, DailyGreetingSwitchCommand.class),
    INFO(ButtonEnum.INFO, InfoCommand.class),
    CAT(ButtonEnum.CAT, WannaCatCommand.class),
    TO_MAIN(ButtonEnum.TO_MAIN, BotChatSettingCommand.class);

    private final ButtonEnum button;
    private final Class<? extends Command> clazz;

    public static Class<? extends Command> getCommandClass(String command){
        return Arrays.stream(CallbackQueryType.values())
                .filter(type -> type.getButton().getCommand().equals(command))
                .findFirst()
                .orElseThrow()
                .getClazz();
    }

    public String getCommandToString(){
        return this.getButton().getCommand();
    }

}
