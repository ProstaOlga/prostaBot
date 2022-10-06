package com.inventory.prosta.bot.service.comands;


import com.inventory.prosta.bot.model.UpdateContext;
import com.inventory.prosta.bot.telegram.handler.keyboard.InlineKeyboardBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;

@Component
@RequiredArgsConstructor
public class NotificationSettingsCommand implements Command{

    private final InlineKeyboardBuilder inlineKeyboardBuilder;
    private final UpdateContext updateContext;

    @Override
    public BotApiMethod<?> execute() {
        Long chatId = updateContext.getChatId();
        Integer messageId = updateContext.getMessageId();

        return EditMessageReplyMarkup.builder()
                .chatId(chatId)
                .messageId(messageId)
                .replyMarkup(inlineKeyboardBuilder.getNotificationKeyboard())
                .build();
    }
}
