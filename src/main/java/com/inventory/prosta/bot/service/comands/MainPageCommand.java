package com.inventory.prosta.bot.service.comands;

import com.inventory.prosta.bot.model.UpdateContext;
import com.inventory.prosta.bot.service.api.MessageService;
import com.inventory.prosta.bot.telegram.handler.keyboard.InlineKeyboardBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;

@Component
@RequiredArgsConstructor
public class MainPageCommand implements Command {
   private final InlineKeyboardBuilder inlineKeyboardBuilder;
   private final UpdateContext updateContext;
   private final MessageService messageService;

    @Override
    public BotApiMethod<?> execute() {
        Long chatId = updateContext.getChatId();
        Integer messageId = updateContext.getMessageId();

        return updateContext.getUpdate().hasCallbackQuery()
                ? editMessage(chatId, messageId)
                : sendMessage(chatId);
    }

    private SendMessage sendMessage(Long chatId){
        return SendMessage.builder()
                .text("Привет, я бот!")
                .chatId(chatId)
                .replyMarkup(inlineKeyboardBuilder.getStartPageKeyboard())
                .build();
    }

    private EditMessageReplyMarkup editMessage(Long chatId, Integer messageId){
        return EditMessageReplyMarkup.builder()
                .chatId(chatId)
                .messageId(messageId)
                .replyMarkup(inlineKeyboardBuilder.getStartPageKeyboard())
                .build();
    }


}
