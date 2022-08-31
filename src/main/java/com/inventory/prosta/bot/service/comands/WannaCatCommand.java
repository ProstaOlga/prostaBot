package com.inventory.prosta.bot.service.comands;

import com.inventory.prosta.bot.model.UpdateContext;
import com.inventory.prosta.bot.model.enums.MediaType;
import com.inventory.prosta.bot.service.api.MessageService;
import com.inventory.prosta.bot.telegram.keyboard.InlineKeyboardBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;

@Component
@RequiredArgsConstructor
public class WannaCatCommand implements Command {

    private final MessageService messageService;
    private final InlineKeyboardBuilder inlineKeyboardBuilder;
    private final UpdateContext updateContext;

    @Override
    public BotApiMethod<?> execute() {
        Long chatId = updateContext.getChatId();

        messageService.sendMessageToChat(MediaType.CAT_DAY, chatId);
        return SendMessage.builder()
                .chatId(chatId)
                .text("мяу")
                .replyMarkup(inlineKeyboardBuilder.getCatKeyboard())
                .build();
    }
}
