package com.inventory.prosta.bot.service.comands;

import com.inventory.prosta.bot.model.UpdateContext;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

public interface Command {

    BotApiMethod<?> execute();
}
