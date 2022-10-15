package com.inventory.prosta.bot.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TextUtil {

    public static String getStringDay(LocalDate localDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM");
        return localDate.format(formatter);
    }
}
