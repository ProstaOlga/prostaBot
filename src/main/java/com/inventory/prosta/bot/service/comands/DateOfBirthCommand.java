package com.inventory.prosta.bot.service.comands;

import com.inventory.prosta.bot.model.UpdateContext;
import com.inventory.prosta.bot.telegram.keyboard.InlineKeyboardBuilder;
import com.inventory.prosta.bot.util.ResourceBundleUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Component
@RequiredArgsConstructor
public class DateOfBirthCommand implements Command {

    private final InlineKeyboardBuilder inlineKeyboardBuilder;
    private final UpdateContext updateContext;
    private final static String TEXT_SETTINGS_BIRTHDAY = ResourceBundleUtil.getMessageText("settings.birthday");

    @Override
    public BotApiMethod<?> execute() {
        Long chatId = updateContext.getChatId();

        return EditMessageText.builder()
                .chatId(chatId)
                .messageId(updateContext.getMessageId())
                .text(TEXT_SETTINGS_BIRTHDAY)
                .replyMarkup(inlineKeyboardBuilder.getChatUsersKeyboard())
                .build();
    }
}
