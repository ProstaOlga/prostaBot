package com.inventory.prosta.bot.service.comands;

import com.inventory.prosta.bot.model.UpdateContext;
import com.inventory.prosta.bot.telegram.handler.keyboard.InlineKeyboardBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Component
@RequiredArgsConstructor
public class SettingsCommand implements Command {

    private final InlineKeyboardBuilder inlineKeyboardBuilder;
    private final UpdateContext updateContext;
    private final String TEXT = "Настройки";

    @Override
    public BotApiMethod<?> execute() {
        Long chatId = updateContext.getChatId();
        Integer messageId = updateContext.getUpdate().getCallbackQuery().getMessage().getMessageId();

        return EditMessageText.builder()
                .chatId(chatId)
                .messageId(messageId)
                .text(TEXT)
                .replyMarkup(inlineKeyboardBuilder.getSettingsKeyboard())
                .build();

//        return SendMessage.builder()
//                .text("Настройки:")
//                .chatId(chatId)
//                .replyMarkup(inlineKeyboardBuilder.getSettingsKeyboard())
//                .build();
    }
}
