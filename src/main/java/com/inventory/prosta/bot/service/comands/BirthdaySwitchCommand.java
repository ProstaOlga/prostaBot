package com.inventory.prosta.bot.service.comands;

import com.inventory.prosta.bot.model.enums.CallbackQueryType;
import com.inventory.prosta.bot.service.api.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import com.inventory.prosta.bot.model.UpdateContext;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class BirthdaySwitchCommand implements Command {

    private final ChatService chatService;
    private final UpdateContext updateContext;

    @Override
    public BotApiMethod<?> execute() {
        Long chatId = updateContext.getChatId();

        return SendMessage.builder()
                .text("Уведомления о днях рождения были отключены")
                .chatId(chatId)
                .build();
    }
}

