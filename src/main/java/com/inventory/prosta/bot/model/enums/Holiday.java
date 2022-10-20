package com.inventory.prosta.bot.model.enums;

import com.inventory.prosta.bot.service.comands.Command;
import com.inventory.prosta.bot.util.ResourceBundleUtil;
import lombok.Getter;

import javax.ws.rs.NotFoundException;
import java.time.LocalDate;
import java.time.MonthDay;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
@Getter
public enum Holiday {
    NEW_YEAR(MonthDay.of(1, 1), MediaType.NEW_YEAR, ResourceBundleUtil.getMessageText("message.holiday.newYear")),
    WOMEN_DAY(MonthDay.of(3, 8), MediaType.WOMEN_DAY, ResourceBundleUtil.getMessageText("message.holiday.womenDay")),
    MENS_DAY(MonthDay.of(2, 23), MediaType.MENS_DAY, ResourceBundleUtil.getMessageText("message.holiday.mensDay")),
    CHRISTMAS(MonthDay.of(1, 14), MediaType.CHRISTMAS, ResourceBundleUtil.getMessageText("message.holiday.christmas")),
    CAT_DAY(MonthDay.of(3, 1), MediaType.CAT_DAY, ResourceBundleUtil.getMessageText("message.holiday.catDay")),
    MOTHERS_DAY(MonthDay.of(11,27), MediaType.MOTHERS_DAY, ResourceBundleUtil.getMessageText("message.holiday.motherDay")),
    FATHERS_DAY(MonthDay.of(10,16), MediaType.FATHERS_DAY, ResourceBundleUtil.getMessageText("message.holiday.fatherDay")),
    SELF_CARE_DAY(MonthDay.of(7, 24), MediaType.SELF_CARE_DAY, ResourceBundleUtil.getMessageText("message.holiday.selfCareDay")),
    VALENTINES_DAY(MonthDay.of(2,14), MediaType.VALENTINES_DAY, ResourceBundleUtil.getMessageText("message.holiday.valentinesDay"));

    private final MonthDay monthDay;
    private final MediaType mediaType;
    private final String messageText;

    Holiday(MonthDay monthDay, MediaType mediaType, String messageText){
        this.monthDay = monthDay;
        this.mediaType = mediaType;
        this.messageText = messageText;
    }

    public static List<Holiday> getHolidays(MonthDay monthDay){
        return Arrays.stream(Holiday.values())
                .filter(holiday -> holiday.monthDay.equals(monthDay))
                .collect(Collectors.toList());
    }

    public static MediaType getMediaType(Holiday holiday){
        return Arrays.stream(Holiday.values())
                .filter(h -> h.equals(holiday))
                .findFirst()
                .orElseThrow(() -> new NotFoundException(String.format("Media type for this holiday: %s, not found", holiday)))
                .getMediaType();
    }

}
