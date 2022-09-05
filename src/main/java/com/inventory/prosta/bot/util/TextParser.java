package com.inventory.prosta.bot.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TextParser {
    @Value("${telegrambot.userName}")
    String botName;


    public String parseMessageText(String message) {
        return message.contains(botName) ? message.replace(botName, "") : message;
    }
}
