package com.inventory.prosta.bot.service.comands;

import com.inventory.prosta.bot.telegramUtil.TelegramBotContext;
import com.inventory.prosta.bot.telegramUtil.keyboard.InlineKeyboardBuilder;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

@Component
@RequiredArgsConstructor
public class BotChatSettingCommand implements Command {

   private final TelegramBotContext telegramBotContext;
   private final InlineKeyboardBuilder inlineKeyboardBuilder;

    @Override
    public BotApiMethod<?> execute(Update update) {
        Long chatId = update.getMessage().getChatId();

        return SendMessage.builder()
                .text("Привет, я бот!")
                .chatId(chatId)
                .replyMarkup(inlineKeyboardBuilder.getStartPageKeyboard())
                .build();
    }
}
