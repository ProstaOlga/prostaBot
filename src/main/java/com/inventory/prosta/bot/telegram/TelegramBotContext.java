package com.inventory.prosta.bot.telegram;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendAnimation;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.send.SendVideo;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

import java.io.Serializable;
import java.util.concurrent.CompletableFuture;

@Component
@Getter
public class TelegramBotContext {

    @Getter
    @Setter
    private TelegramBot telegramBot;

    @Value("${telegrambot.botId}")
    private Long botId;

    @Value("${telegrambot.admin.id}")
    private Long adminId;

    @SneakyThrows
    public void execute(SendMessage sendMessage) {
        this.telegramBot.execute(sendMessage);
    }

    @SneakyThrows
    public void execute(SendPhoto sendPhoto) {
        this.telegramBot.execute(sendPhoto);
    }

    @SneakyThrows
    public void execute(SendVideo sendVideo) {
        this.telegramBot.execute(sendVideo);
    }

    @SneakyThrows
    public void execute(DeleteMessage deleteMessage) {
        this.telegramBot.execute(deleteMessage);
    }

    @SneakyThrows
    public void execute(EditMessageText editMessageText) {
        this.telegramBot.execute(editMessageText);
    }


}
