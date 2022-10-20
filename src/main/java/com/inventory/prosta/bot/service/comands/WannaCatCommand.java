package com.inventory.prosta.bot.service.comands;

import com.inventory.prosta.bot.model.UpdateContext;
import com.inventory.prosta.bot.service.api.CatImageService;
import com.inventory.prosta.bot.service.api.MessageService;
import com.inventory.prosta.bot.telegram.doubleClickProtection.DoubleClickProtector;
import com.inventory.prosta.bot.telegram.keyboard.InlineKeyboardBuilder;
import com.inventory.prosta.bot.util.ResourceBundleUtil;
import com.inventory.prosta.bot.util.Symbols;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
@RequiredArgsConstructor
public class WannaCatCommand implements Command {

    private final static String TEXT_CAT = ResourceBundleUtil.getMessageText("wannacat.take.cat");

    private final MessageService messageService;
    private final InlineKeyboardBuilder inlineKeyboardBuilder;
    private final UpdateContext updateContext;
    private final CatImageService catImageService;
    private final DoubleClickProtector doubleClickProtector;

    @Override
    public BotApiMethod<?> execute() {
        return doubleClickProtector.checkDoubleClick() ? null : sendCatImage();

    }

    private BotApiMethod<?> sendCatImage() {
        Long chatId = updateContext.getChatId();

        messageService.sendMediaToChat(chatId, catImageService.getRandomCatMedia());
        messageService.deleteMessage(updateContext.getChatId(), updateContext.getUpdate().getCallbackQuery().getMessage().getMessageId());

        return SendMessage.builder()
                .chatId(chatId)
                .text(TEXT_CAT + Symbols.CAT_EMOJI)
                .replyMarkup(inlineKeyboardBuilder.getCatKeyboard())
                .build();
    }
}
