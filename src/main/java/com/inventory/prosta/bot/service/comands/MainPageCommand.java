package com.inventory.prosta.bot.service.comands;

import com.inventory.prosta.bot.model.UpdateContext;
import com.inventory.prosta.bot.service.api.MessageService;
import com.inventory.prosta.bot.telegram.keyboard.InlineKeyboardBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Component
@RequiredArgsConstructor
public class MainPageCommand implements Command {
   private final InlineKeyboardBuilder inlineKeyboardBuilder;
   private final UpdateContext updateContext;
   private final String TEXT = "Привет, я бот!";

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
                .text(TEXT)
                .chatId(chatId)
                .replyMarkup(inlineKeyboardBuilder.getStartPageKeyboard())
                .build();
    }

    private EditMessageText editMessage(Long chatId, Integer messageId){
        return EditMessageText.builder()
                .chatId(chatId)
                .messageId(messageId)
                .text(TEXT)
                .replyMarkup(inlineKeyboardBuilder.getStartPageKeyboard())
                .build();
    }


}
