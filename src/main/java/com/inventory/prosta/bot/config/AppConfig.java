package com.inventory.prosta.bot.config;

import com.inventory.prosta.bot.telegram.TelegramBot;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class AppConfig {

    @Value("${telegrambot.webHookPath}")
    private String webHookPath;

    @Value("${telegrambot.userName}")
    private String userName;

    @Value("${telegrambot.botToken}")
    private String botToken;

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

    @Bean
    public void bindWebHook() {
        var link = "https://api.telegram.org/bot" + botToken + "/setWebhook?url=";
        var response = new RestTemplate().getForEntity(link + webHookPath, Object.class);
        log.info("WebHook info: {}", response);
    }

}
