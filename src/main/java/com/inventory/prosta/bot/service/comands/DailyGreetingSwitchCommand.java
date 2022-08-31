package com.inventory.prosta.bot.service.comands;

import com.inventory.prosta.bot.model.UpdateContext;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
@Component
public class DailyGreetingSwitchCommand implements Command{
    @Override
    public BotApiMethod<?> execute() {
        return null;
    }
}
