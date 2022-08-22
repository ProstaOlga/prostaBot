package com.inventory.prosta.bot.model;

import java.time.LocalDate;
import java.time.MonthDay;

public enum Holiday {
    NEW_YEAR(MonthDay.of(1, 1)),
    WOMEN_DAY(MonthDay.of(3, 8)),
    MENS_DAY(MonthDay.of(2, 23)),
    CHRISTMAS(MonthDay.of(1, 14)),
    CAT_DAY(MonthDay.of(3, 1)),
    MOTHERS_DAY(MonthDay.of(11,27)),
    FATHERS_DAY(MonthDay.of(10,16));

    private MonthDay monthDay;
    private LocalDate date;

    Holiday(MonthDay monthDay){
        this.monthDay = monthDay;
    }

}
