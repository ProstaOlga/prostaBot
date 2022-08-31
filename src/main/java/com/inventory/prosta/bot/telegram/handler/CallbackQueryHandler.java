package com.inventory.prosta.bot.telegram.handler;

import com.inventory.prosta.bot.model.UpdateContext;
import com.inventory.prosta.bot.model.enums.CallbackQueryType;
import com.inventory.prosta.bot.service.api.ChatService;
import com.inventory.prosta.bot.service.api.MediaService;
import com.inventory.prosta.bot.service.api.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Chat;
import com.inventory.prosta.bot.model.UpdateContext;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class CallbackQueryHandler {
    private final UpdateContext updateContext;

    private final ApplicationContext applicationContext;
    private final ChatService chatService;
    private final MessageService messageService;

    public BotApiMethod<?> processCallbackQuery(Update update) {
        CallbackQuery buttonQuery = update.getCallbackQuery();
        updateContext.setValueFromCallbackQuery(update);

        chatService.authenticateAndRegistrationNewChat(updateContext.getChat());
       
        var commandClass = CallbackQueryType.getCommandClass(buttonQuery.getData());
        var command = applicationContext.getBean(commandClass);

        messageService.deleteMessageT(updateContext.getChatId(), updateContext.getUpdate().getCallbackQuery().getMessage().getMessageId());

        return command.execute();
    }
}
