package com.inventory.prosta.bot.config;

import com.inventory.prosta.bot.telegramUtil.TelegramBot;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;

@Configuration
@RequiredArgsConstructor
public class AppConfig {

    private final TelegramBotConfig botConfig;

    @Bean
    public SetWebhook setWebhookInstance(){
       return SetWebhook.builder().url(botConfig.getWebHookPath()).build();
    }

    @Bean
    public TelegramBot springWebhookBot(SetWebhook setWebhook){
        TelegramBot bot = new TelegramBot(setWebhook);
        bot.setUserName(botConfig.getUserName());
        bot.setBotToken(botConfig.getBotToken());
        bot.setWebHookPath(botConfig.getWebHookPath());

        return bot;
    }




}
