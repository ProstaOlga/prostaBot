package com.inventory.prosta.bot.service.comands;

import com.inventory.prosta.bot.model.UpdateContext;
import com.inventory.prosta.bot.telegram.keyboard.InlineKeyboardBuilder;
import com.inventory.prosta.bot.util.ResourceBundleUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Component
@RequiredArgsConstructor
public class SettingsCommand implements Command {

    private final InlineKeyboardBuilder inlineKeyboardBuilder;
    private final UpdateContext updateContext;
    private final String TEXT_MAIN = ResourceBundleUtil.getMessageText("settings.main");

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
                .text(TEXT_MAIN)
                .chatId(chatId)
                .replyMarkup(inlineKeyboardBuilder.getSettingsKeyboard())
                .build();
    }

    private EditMessageText editMessage(Long chatId, Integer messageId){
        return EditMessageText.builder()
                .chatId(chatId)
                .messageId(messageId)
                .text(TEXT_MAIN)
                .replyMarkup(inlineKeyboardBuilder.getSettingsKeyboard())
                .build();
    }
}
