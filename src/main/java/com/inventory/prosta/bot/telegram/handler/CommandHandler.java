package com.inventory.prosta.bot.telegram.handler;

import com.inventory.prosta.bot.model.UpdateContext;
import com.inventory.prosta.bot.model.enums.MessageType;
import com.inventory.prosta.bot.service.aspect.Auth;
import com.inventory.prosta.bot.util.TextParser;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class CommandHandler implements TelegramHandler{
    private final ApplicationContext applicationContext;
    private final UpdateContext updateContext;
    private final TextParser textParser;
    @Auth
    @Override
    public BotApiMethod<?> handle(Update update) {
        Message message = update.getMessage();

        return message == null ? null : executeCommand(update);
    }

    private BotApiMethod<?> executeCommand(Update update){
        String message = update.getMessage().getText();
        updateContext.setValueFromMessage(update);

        var commandClass = MessageType.getCommandClass(textParser.parseMessageText(message));
        var command = applicationContext.getBean(commandClass);

        return command.execute();
    }
}
