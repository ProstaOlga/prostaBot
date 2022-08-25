package com.inventory.prosta.bot.service.comands;

import com.inventory.prosta.bot.telegramUtil.TelegramBotContext;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

@Component
@RequiredArgsConstructor
public class BotChatSettingCommand implements Command {
   private final TelegramBotContext telegramBotContext;

    @Override
    public BotApiMethod<?> execute(Update update) {
        String chatId = update.getMessage().getChatId().toString();
        telegramBotContext.execute(new SendMessage(chatId, "Тест"));
        return new SendMessage(chatId, "Привет я бот");
    }
}
