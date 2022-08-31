package com.inventory.prosta.bot.service.comands;

import com.inventory.prosta.bot.telegram.TelegramBotContext;
import com.inventory.prosta.bot.telegram.keyboard.InlineKeyboardBuilder;
import jooq.tables.daos.AccountDao;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import com.inventory.prosta.bot.model.UpdateContext;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class BotChatSettingCommand implements Command {
   private final InlineKeyboardBuilder inlineKeyboardBuilder;
   private final UpdateContext updateContext;

    @Override
    public BotApiMethod<?> execute() {
        Long chatId = updateContext.getChatId();

        return SendMessage.builder()
                .text("Привет, я бот! Посмотри на мои настроечки:")
                .chatId(chatId)
                .replyMarkup(inlineKeyboardBuilder.getStartPageKeyboard())
                .build();
    }


}
