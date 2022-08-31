package com.inventory.prosta.bot.telegram.handler;

import com.inventory.prosta.bot.model.enums.MessageType;
import com.inventory.prosta.bot.service.api.ChatService;
import com.inventory.prosta.bot.service.api.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import com.inventory.prosta.bot.model.UpdateContext;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class MessageHandler {
    private final ApplicationContext applicationContext;
    private final ChatService chatService;
    private final UpdateContext updateContext;
    private final MessageService messageService;

    public BotApiMethod<?> processMessage(Update update) {
        Message message = update.getMessage();

        return message == null ? null : executeCommand(update);
    }

    private BotApiMethod<?> executeCommand(Update update){
        Message message = update.getMessage();
        updateContext.setValueFromMessage(update);

        chatService.authenticateAndRegistrationNewChat(message.getChat());

        var commandClass = MessageType.getCommandClass(message.getText());
        var command = applicationContext.getBean(commandClass);

        messageService.deleteMessageT(updateContext.getChatId(), updateContext.getUpdate().getMessage().getMessageId());

        return command.execute();
    }
}
