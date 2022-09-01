package com.inventory.prosta.bot.telegram;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;

import java.util.concurrent.CompletableFuture;

@Component
public class TelegramBotContext {

    @Getter
    @Setter
    private TelegramBot telegramBot;

    @SneakyThrows
    public void execute(SendMessage sendMessage) {
        this.telegramBot.execute(sendMessage);
    }

    @SneakyThrows
    public void execute(SendPhoto sendPhoto) {
        this.telegramBot.execute(sendPhoto);
    }

    @SneakyThrows
    public void execute(SendPhoto sendPhoto, SendMessage sendMessage) {
        this.telegramBot.execute(sendPhoto);
        this.telegramBot.execute(sendMessage);
    }

    @SneakyThrows
    public void execute(DeleteMessage deleteMessage) {
        this.telegramBot.execute(deleteMessage);
    }
}
