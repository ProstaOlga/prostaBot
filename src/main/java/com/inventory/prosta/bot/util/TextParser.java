package com.inventory.prosta.bot.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TextParser {
    @Value("${telegrambot.userName}")
    String botName;
    String COMMAND_PARAMS_REGEX = "&.*$";
    String COMMAND_REGEX = "^.+&";



    public String parseMessageText(String message) {
        return message.contains(botName) ? message.replace(botName, "") : message;
    }

    public String parseDataText(String data) {
        return StringUtils.substringBefore(data, "&");
    }

    public String getDataParams(String data) {
        return StringUtils.substringAfterLast(data, "&");
    }
}
