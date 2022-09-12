package com.inventory.prosta.bot.model.enums;

import com.inventory.prosta.bot.service.comands.Command;
import lombok.Getter;

import javax.ws.rs.NotFoundException;
import java.time.LocalDate;
import java.time.MonthDay;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
@Getter
public enum Holiday {
    NEW_YEAR(MonthDay.of(1, 1), MediaType.NEW_YEAR),
    WOMEN_DAY(MonthDay.of(3, 8), MediaType.WOMEN_DAY),
    MENS_DAY(MonthDay.of(2, 23), MediaType.MENS_DAY),
    CHRISTMAS(MonthDay.of(1, 14), MediaType.CHRISTMAS),
    CAT_DAY(MonthDay.of(3, 1), MediaType.CAT_DAY),
    MOTHERS_DAY(MonthDay.of(11,27), MediaType.MOTHERS_DAY),
    FATHERS_DAY(MonthDay.of(10,16), MediaType.FATHERS_DAY),
    SELF_CARE_DAY(MonthDay.of(7, 24), MediaType.SELF_CARE_DAY);

    private MonthDay monthDay;
    private MediaType mediaType;

    Holiday(MonthDay monthDay, MediaType mediaType){
        this.monthDay = monthDay;
        this.mediaType = mediaType;
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
