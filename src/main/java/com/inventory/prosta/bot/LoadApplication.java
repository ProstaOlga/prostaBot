package com.inventory.prosta.bot;

import com.inventory.prosta.bot.telegramUtil.TelegramBotContext;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@SpringBootApplication
public class LoadApplication {
    public static void main(String[] args) {
        SpringApplication.run(LoadApplication.class, args);
    }
}
