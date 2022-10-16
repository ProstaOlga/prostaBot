package com.inventory.prosta.bot.telegram.handler;

import com.inventory.prosta.bot.model.UpdateContext;
import com.inventory.prosta.bot.model.enums.CallbackQueryType;
import com.inventory.prosta.bot.service.aspect.Auth;
import com.inventory.prosta.bot.service.aspect.UpdateAccountData;
import com.inventory.prosta.bot.util.TextParser;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class CallbackQueryHandler implements TelegramHandler{
    private final UpdateContext updateContext;
    private final ApplicationContext applicationContext;
    private final TextParser textParser;

    @Auth
    @Override
    @UpdateAccountData
    public BotApiMethod<?> handle(Update update) {
        CallbackQuery buttonQuery = update.getCallbackQuery();
        updateContext.setValueFromCallbackQuery(update);

        var commandClass = CallbackQueryType.getCommandClass(textParser.parseDataText(buttonQuery.getData()));
        var command = applicationContext.getBean(commandClass);

        return command.execute();
    }
}
