package com.inventory.prosta.bot.service.comands;

import com.inventory.prosta.bot.model.enums.ButtonEnum;
import com.inventory.prosta.bot.service.api.ChatService;
import com.inventory.prosta.bot.telegram.handler.keyboard.InlineKeyboardBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import com.inventory.prosta.bot.model.UpdateContext;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;

@Component
@RequiredArgsConstructor
public class BirthdaySwitchCommand implements Command {

    private final ChatService chatService;
    private final UpdateContext updateContext;
    private final InlineKeyboardBuilder inlineKeyboardBuilder;

    @Override
    public BotApiMethod<?> execute() {
        Long chatId = updateContext.getChatId();
        String command = updateContext.getUpdate().getCallbackQuery().getData();
        Integer messageId = updateContext.getMessageId();

        return command.equals(ButtonEnum.BIRTHDAY_CONGRATULATION_DISABLE.getCommand()) ?
                disableBirthdayNotice(chatId, messageId)
                : enableBirthdayNotice(chatId, messageId);
    }

    private EditMessageReplyMarkup disableBirthdayNotice(Long chatId, Integer messageId){
        chatService.offBirthdayNotice(chatId);

        return EditMessageReplyMarkup.builder()
                .chatId(chatId)
                .messageId(messageId)
                .replyMarkup(inlineKeyboardBuilder.getNotificationKeyboard())
                .build();
    }

    private EditMessageReplyMarkup enableBirthdayNotice(Long chatId, Integer messageId){
        chatService.onBirthdayNotice(chatId);

        return EditMessageReplyMarkup.builder()
                .chatId(chatId)
                .messageId(messageId)
                .replyMarkup(inlineKeyboardBuilder.getNotificationKeyboard())
                .build();
    }
}

