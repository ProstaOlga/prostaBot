package com.inventory.prosta.bot.config;

import com.inventory.prosta.bot.model.TelegramBot;
import com.inventory.prosta.bot.model.TelegramFacade;
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
    public TelegramBot springWebhookBot(SetWebhook setWebhook, TelegramFacade telegramFacade){
        TelegramBot bot = new TelegramBot(telegramFacade, setWebhook);
        bot.setUserName(botConfig.getUserName());
        bot.setBotToken(botConfig.getBotToken());
        bot.setWebHookPath(botConfig.getWebHookPath());

        return bot;
    }




}
