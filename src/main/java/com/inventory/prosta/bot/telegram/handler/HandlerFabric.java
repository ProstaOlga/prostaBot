package com.inventory.prosta.bot.telegram.handler;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;


@Getter
@Setter
@Component
@RequiredArgsConstructor
public class HandlerFabric {

    public Class<? extends TelegramHandler> getHandlerClass(Update update) {
        return update.hasCallbackQuery() ? CallbackQueryHandler.class : messageDispatch(update);
    }

    private Class<? extends TelegramHandler> messageDispatch(Update update) {
        return messageIsCommand(update) ? CommandHandler.class : AnswerHandler.class;
    }

    private boolean messageIsCommand(Update update) {
        String text = update.getMessage().getText();

        return text != null && text.startsWith("/");
    }


}
