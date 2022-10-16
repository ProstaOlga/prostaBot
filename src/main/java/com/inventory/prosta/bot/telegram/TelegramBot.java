package com.inventory.prosta.bot.telegram;

import com.inventory.prosta.bot.model.answer.AnswerEvent;
import com.inventory.prosta.bot.service.api.ChatService;
import com.inventory.prosta.bot.service.comands.MainPageCommand;
import com.inventory.prosta.bot.telegram.handler.AnswerHandler;
import com.inventory.prosta.bot.telegram.handler.CallbackQueryHandler;
import com.inventory.prosta.bot.telegram.handler.CommandHandler;
import com.inventory.prosta.bot.telegram.handler.HandlerFabric;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.starter.SpringWebhookBot;

import javax.annotation.PostConstruct;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TelegramBot extends SpringWebhookBot {
    String webHookPath;
    String userName;
    String botToken;

    private MainPageCommand mainPageCommand;
    private TelegramBotContext telegramBotContext;
    private ChatService chatService;
    private HandlerFabric handlerFabric;
    private ApplicationContext applicationContext;

    @Autowired
    public void setMainPageCommand(MainPageCommand mainPageCommand) {
        this.mainPageCommand = mainPageCommand;
    }

    @Autowired
    public void setTelegramBotContext(TelegramBotContext telegramBotContext) {
        this.telegramBotContext = telegramBotContext;
    }

    @Autowired
    public void setChatService(ChatService chatService) {
        this.chatService = chatService;
    }

    @Autowired
    public void setHandlerFabric(HandlerFabric handlerFabric) {
        this.handlerFabric = handlerFabric;
    }

    @Autowired
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public TelegramBot(DefaultBotOptions options, SetWebhook setWebhook) {
        super(options, setWebhook);
    }

    public TelegramBot(SetWebhook setWebhook) {
        super(setWebhook);
    }

    @PostConstruct
    private void setupContext() {
        telegramBotContext.setTelegramBot(this);
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        return isMessageOrUpdate(update) ? handleMessage(update) : null;
    }

    private boolean isMessageOrUpdate(Update update){
        return update.hasCallbackQuery() | update.hasMessage();
    }
    private BotApiMethod<?> handleMessage(Update update){
        var handlerClass =  handlerFabric.getHandlerClass(update);
        var handler = applicationContext.getBean(handlerClass);

        return handler.handle(update);
    }

    @Override
    public String getBotPath() {
        return this.webHookPath;
    }

    @Override
    public String getBotUsername() {
        return this.userName;
    }


}
