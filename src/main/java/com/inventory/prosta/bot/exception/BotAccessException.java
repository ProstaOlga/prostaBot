package com.inventory.prosta.bot.exception;

import lombok.Getter;

@Getter
public class BotAccessException extends RuntimeException {

    private final Long chatId;

    public BotAccessException(Long chatId) {
        super(String.format("Бот не состоит в чате %d",  chatId));
        this.chatId = chatId;
    }
}
