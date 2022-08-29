package com.inventory.prosta.bot.telegram.handler;

import com.inventory.prosta.bot.model.enums.CallbackQueryType;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class CallbackQueryHandler {

    private final ApplicationContext applicationContext;

    public BotApiMethod<?> processCallbackQuery(Update update) {
        CallbackQuery buttonQuery = update.getCallbackQuery();
        var commandClass = CallbackQueryType.getCommandClass(buttonQuery.getData());
        var command = applicationContext.getBean(commandClass);

        return command.execute(update);
    }
}
