package com.inventory.prosta.bot.telegram.handler;

import com.inventory.prosta.bot.BaseUnitTest;
import com.inventory.prosta.bot.model.enums.MessageType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

class HandlerFabricTest extends BaseUnitTest {

    private final HandlerFabric handlerFabric = new HandlerFabric();

    @Test
    void getHandlerClassCallbackQueryHandler() {
        Update update = new Update();
        update.setCallbackQuery(new CallbackQuery());

        var handlerClass = handlerFabric.getHandlerClass(update);

        Assertions.assertEquals(CallbackQueryHandler.class, handlerClass);
    }

    @Test
    void getHandlerClassAnswerHandler() {
        Update update = new Update();
        Message commandMessage = new Message();
        commandMessage.setText("Any text");
        update.setMessage(commandMessage);

        var handlerClass = handlerFabric.getHandlerClass(update);

        Assertions.assertEquals(AnswerHandler.class, handlerClass);
    }

    @Test
    void getHandlerClassCallbackCommandHandler() {
        Update update = new Update();
        Message commandMessage = new Message();
        commandMessage.setText(MessageType.BOT_CHAT_START.getText());
        update.setMessage(commandMessage);

        var handlerClass = handlerFabric.getHandlerClass(update);

        Assertions.assertEquals(CommandHandler.class, handlerClass);
    }
}