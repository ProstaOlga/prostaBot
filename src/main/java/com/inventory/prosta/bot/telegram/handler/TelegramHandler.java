package com.inventory.prosta.bot.telegram.handler;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface TelegramHandler {
    BotApiMethod<?> handle(Update update);
}
