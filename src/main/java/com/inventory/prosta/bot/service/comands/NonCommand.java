package com.inventory.prosta.bot.service.comands;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
@Component
public class NonCommand implements Command{
    @Override
    public BotApiMethod<?> execute(Update update) {
        return null;
    }
}
