package com.inventory.prosta.bot.model.enums;

import com.inventory.prosta.bot.service.comands.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum CallbackQueryType {
    NOTIFICATIONS(ButtonEnum.NOTIFICATIONS, NotificationsCommand.class),
    HOLIDAY_CONGRATULATION_ON(ButtonEnum.HOLIDAY_CONGRATULATION_ON, HolidaySwitchCommand.class),
    HOLIDAY_CONGRATULATION_OFF(ButtonEnum.HOLIDAY_CONGRATULATION_OFF, HolidaySwitchCommand.class),
    BIRTHDAY_CONGRATULATION_ON(ButtonEnum.BIRTHDAY_CONGRATULATION_ON, BirthdaySwitchCommand.class),
    BIRTHDAY_CONGRATULATION_OFF(ButtonEnum.BIRTHDAY_CONGRATULATION_OFF, BirthdaySwitchCommand.class),
    DAILY_GREETING_ON(ButtonEnum.DAILY_GREETING_ON, DailyGreetingSwitchCommand.class),
    DAILY_GREETING_OF(ButtonEnum.DAILY_GREETING_OF, DailyGreetingSwitchCommand.class),
    INFO(ButtonEnum.INFO, InfoCommand.class);

    private final ButtonEnum button;
    private final Class<? extends Command> clazz;

    public static Class<? extends Command> getCommandClass(String command){
        return Arrays.stream(CallbackQueryType.values())
                .filter(type -> type.getButton().getCommand().equals(command))
                .findFirst()
                .orElseThrow()
                .getClazz();
    }


}
