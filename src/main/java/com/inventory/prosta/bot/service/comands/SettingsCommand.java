package com.inventory.prosta.bot.service.comands;

import com.inventory.prosta.bot.model.UpdateContext;
import com.inventory.prosta.bot.telegram.keyboard.InlineKeyboardBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;

@Component
@RequiredArgsConstructor
public class SettingsCommand implements Command {

    private final InlineKeyboardBuilder inlineKeyboardBuilder;
    private final UpdateContext updateContext;

    @Override
    public BotApiMethod<?> execute() {
        Long chatId = updateContext.getChatId();
        Integer messageId = updateContext.getUpdate().getCallbackQuery().getMessage().getMessageId();

        return EditMessageReplyMarkup.builder()
                .chatId(chatId)
                .messageId(messageId)
                .replyMarkup(inlineKeyboardBuilder.getSettingsKeyboard())
                .build();

//        return SendMessage.builder()
//                .text("Настройки:")
//                .chatId(chatId)
//                .replyMarkup(inlineKeyboardBuilder.getSettingsKeyboard())
//                .build();
    }
}
