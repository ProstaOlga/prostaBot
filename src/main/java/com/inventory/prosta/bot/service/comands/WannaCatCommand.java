package com.inventory.prosta.bot.service.comands;

import com.inventory.prosta.bot.model.UpdateContext;
import com.inventory.prosta.bot.service.api.CatImageService;
import com.inventory.prosta.bot.service.api.MessageService;
import com.inventory.prosta.bot.telegram.keyboard.InlineKeyboardBuilder;
import com.inventory.prosta.bot.util.Symbols;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
@RequiredArgsConstructor
public class WannaCatCommand implements Command {

    private final MessageService messageService;
    private final InlineKeyboardBuilder inlineKeyboardBuilder;
    private final UpdateContext updateContext;
    private final CatImageService catImageService;

    @Override
    public BotApiMethod<?> execute() {
        Long chatId = updateContext.getChatId();

        messageService.sendMediaToChat(catImageService.getRandomCatMedia(), chatId);
        messageService.deleteMessage(updateContext.getChatId(), updateContext.getUpdate().getCallbackQuery().getMessage().getMessageId());

        return SendMessage.builder()
                .chatId(chatId)
                .text("Держи котика!" + Symbols.CAT_EMOJI)
                .replyMarkup(inlineKeyboardBuilder.getCatKeyboard())
                .build();
    }
}
