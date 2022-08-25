package com.inventory.prosta.bot.telegramUtil;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
public class TelegramBotContext {

    @Getter
    @Setter
    private TelegramBot telegramBot;

    @SneakyThrows
    public void execute(SendMessage sendMessage) {
        this.telegramBot.executeAsync(sendMessage);
    }
}
