package com.inventory.prosta.bot.telegram.handler;

import com.inventory.prosta.bot.model.UpdateContext;
import com.inventory.prosta.bot.model.enums.CallbackQueryType;
import com.inventory.prosta.bot.service.api.ChatService;
import com.inventory.prosta.bot.service.api.MessageService;
import com.inventory.prosta.bot.service.aspect.AccountAuth;
import com.inventory.prosta.bot.service.aspect.Auth;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class CallbackQueryHandler {
    private final UpdateContext updateContext;

    private final ApplicationContext applicationContext;
    private final ChatService chatService;
    private final MessageService messageService;

    @Auth
    @AccountAuth
    public BotApiMethod<?> processCallbackQuery(Update update) {
        CallbackQuery buttonQuery = update.getCallbackQuery();
        updateContext.setValueFromCallbackQuery(update);

        var commandClass = CallbackQueryType.getCommandClass(buttonQuery.getData());
        var command = applicationContext.getBean(commandClass);

//        messageService.deleteMessageT(updateContext.getChatId(), updateContext.getUpdate().getCallbackQuery().getMessage().getMessageId());

        return command.execute();
    }
}
