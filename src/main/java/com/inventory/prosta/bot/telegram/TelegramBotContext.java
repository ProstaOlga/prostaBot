package com.inventory.prosta.bot.telegram;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;

@Component
public class TelegramBotContext {

    @Getter
    @Setter
    private TelegramBot telegramBot;

    @SneakyThrows
    public void execute(SendMessage sendMessage) {
        this.telegramBot.executeAsync(sendMessage);
    }

    @SneakyThrows
    public void execute(SendPhoto sendPhoto) {
        this.telegramBot.executeAsync(sendPhoto);
    }

    @SneakyThrows
    public void execute(SendPhoto sendPhoto, SendMessage sendMessage) {
        this.telegramBot.executeAsync(sendPhoto);
        this.telegramBot.executeAsync(sendMessage);
    }
}
