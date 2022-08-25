package com.inventory.prosta.bot.model.enums;

import com.inventory.prosta.bot.service.comands.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum CallbackQueryType {
    NOTIFICATIONS(ButtonNameEnum.NOTIFICATIONS, NotificationsCommand.class),
    HOLIDAY_CONGRATULATION_ON(ButtonNameEnum.HOLIDAY_CONGRATULATION_ON, HolidaySwitchCommand.class),
    HOLIDAY_CONGRATULATION_OFF(ButtonNameEnum.HOLIDAY_CONGRATULATION_OFF, HolidaySwitchCommand.class),
    BIRTHDAY_CONGRATULATION_ON(ButtonNameEnum.BIRTHDAY_CONGRATULATION_ON, BirthdaySwitchCommand.class),
    BIRTHDAY_CONGRATULATION_OFF(ButtonNameEnum.BIRTHDAY_CONGRATULATION_OFF, BirthdaySwitchCommand.class),
    DAILY_GREETING_ON(ButtonNameEnum.DAILY_GREETING_ON, DailyGreetingSwitchCommand.class),
    DAILY_GREETING_OF(ButtonNameEnum.DAILY_GREETING_OF, DailyGreetingSwitchCommand.class);

    private final ButtonNameEnum button;
    private final Class<? extends Command> clazz;

    public static Class<? extends Command> getCommandClass(String command){
        return Arrays.stream(CallbackQueryType.values())
                .filter(type -> type.getButton().getName().equals(command))
                .findFirst()
                .orElseThrow()
                .getClazz();
    }


}
