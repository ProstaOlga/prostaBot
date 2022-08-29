package com.inventory.prosta.bot.service.comands;

import com.inventory.prosta.bot.model.enums.CallbackQueryType;
import com.inventory.prosta.bot.service.api.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class BirthdaySwitchCommand implements Command {

    private final ChatService chatService;

    @Override
    public BotApiMethod<?> execute(Update update) {
        Long chatId = update.getMessage().getChatId();
        String callbackQuery = update.getCallbackQuery().toString();

        return SendMessage.builder()
                .text("Уведомления о днях рождения были отключены")
                .chatId(chatId)
                .build();
    }
}

