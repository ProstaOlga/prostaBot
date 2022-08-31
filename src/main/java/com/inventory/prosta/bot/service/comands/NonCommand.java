package com.inventory.prosta.bot.service.comands;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import com.inventory.prosta.bot.model.UpdateContext;
import org.telegram.telegrambots.meta.api.objects.Update;
@Component
public class NonCommand implements Command{
    @Override
    public BotApiMethod<?> execute() {
        return null;
    }
}
