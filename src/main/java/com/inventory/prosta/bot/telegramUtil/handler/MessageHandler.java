package com.inventory.prosta.bot.telegramUtil.handler;

import com.inventory.prosta.bot.model.enums.CallbackQueryType;
import com.inventory.prosta.bot.model.enums.MessageType;
import com.inventory.prosta.bot.service.comands.Command;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class MessageHandler {

    private final ApplicationContext applicationContext;
    public BotApiMethod<?> processMessage(Update update) {
        Message message = update.getMessage();

        return message == null ? null : executeCommand(update);
    }

    private BotApiMethod<?> executeCommand(Update update){
        Message message = update.getMessage();
        var commandClass = MessageType.getCommandClass(message.getText());
        var command = applicationContext.getBean(commandClass);

        return command.execute(update);
    }
}
