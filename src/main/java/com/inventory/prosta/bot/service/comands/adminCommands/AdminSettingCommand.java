package com.inventory.prosta.bot.service.comands.adminCommands;

import com.inventory.prosta.bot.model.UpdateContext;
import com.inventory.prosta.bot.service.api.MediaService;
import com.inventory.prosta.bot.service.comands.Command;
import com.inventory.prosta.bot.telegram.TelegramBotContext;
import com.inventory.prosta.bot.telegram.keyboard.InlineKeyboardBuilder;
import com.inventory.prosta.bot.util.Symbols;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class AdminSettingCommand implements Command {

    private final MediaService mediaService;
    private final UpdateContext updateContext;
    private final InlineKeyboardBuilder inlineKeyboardBuilder;
    private final TelegramBotContext telegramBotContext;
    private final static String TEXT = "Настройки администратора";

    private final static String AUTH_ERROR_TEXT = Symbols.WARNING + "У вас нет доступа к настройкам администратора";


    @Override
    public BotApiMethod<?> execute() {
        Long chatId = updateContext.getChatId();
        Integer messageId = updateContext.getMessageId();
        Long userId = updateContext.getUserId();

        return !adminAuth(userId)
                ? errorAuth(chatId)
                : updateContext.getUpdate().hasCallbackQuery()
                ? editMessage(chatId, messageId)
                : sendMessage(chatId);
    }

    private boolean adminAuth(Long userId) {
        return Objects.equals(telegramBotContext.getAdminId(), userId);
    }

    private BotApiMethod<?> errorAuth(Long chatId) {
        return SendMessage.builder()
                .text(AUTH_ERROR_TEXT)
                .chatId(chatId)
                .build();
    }

    private BotApiMethod<?> sendMessage(Long chatId) {
        return SendMessage.builder()
                .text(TEXT)
                .chatId(chatId)
                .replyMarkup(inlineKeyboardBuilder.getAdminPageKeyboard())
                .build();
    }

    private BotApiMethod<?> editMessage(Long chatId, Integer messageId) {
        return EditMessageText.builder()
                .text(TEXT)
                .chatId(chatId)
                .messageId(messageId)
                .replyMarkup(inlineKeyboardBuilder.getAdminPageKeyboard())
                .build();
    }


}
