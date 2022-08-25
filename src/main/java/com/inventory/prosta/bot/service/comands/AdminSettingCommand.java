package com.inventory.prosta.bot.service.comands;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

@Component
public class AdminSettingCommand implements Command{
    @Override
    public BotApiMethod<?> execute(Update update) {
        return null;
    }
}
