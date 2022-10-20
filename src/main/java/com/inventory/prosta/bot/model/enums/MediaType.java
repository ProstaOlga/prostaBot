package com.inventory.prosta.bot.model.enums;

import com.inventory.prosta.bot.service.comands.BirthdaySwitchCommand;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.time.MonthDay;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum MediaType {

    BIRTHDAY,
    NEW_YEAR,
    WOMEN_DAY,
    MENS_DAY,
    CHRISTMAS,
    CAT_DAY,
    MOTHERS_DAY,
    FATHERS_DAY,
    MORNING_GREETING,
    GOOD_NIGHT,
    SELF_CARE_DAY,
    VALENTINES_DAY;

    public static List<MediaType> getMediaTypeList() {
        return Arrays.stream(MediaType.values())
                .collect(Collectors.toList());
    }
}
