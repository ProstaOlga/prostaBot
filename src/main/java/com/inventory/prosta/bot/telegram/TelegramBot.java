package com.inventory.prosta.bot.telegram;

import com.inventory.prosta.bot.service.api.ChatService;
import com.inventory.prosta.bot.service.comands.MainPageCommand;
import com.inventory.prosta.bot.telegram.handler.MessageHandler;
import com.inventory.prosta.bot.telegram.handler.CallbackQueryHandler;
import com.inventory.prosta.bot.telegram.handler.CommandHandler;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
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
    private CommandHandler commandHandler;
    private CallbackQueryHandler callbackQueryHandler;
    private MessageHandler messageHandler;
    private ChatService chatService;

    @Autowired
    public void setMainPageCommand(MainPageCommand mainPageCommand) {
        this.mainPageCommand = mainPageCommand;
    }

    @Autowired
    public void setTelegramBotContext(TelegramBotContext telegramBotContext) {
        this.telegramBotContext = telegramBotContext;
    }

    @Autowired
    public void setCommandHandler(CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    @Autowired
    public void setMessageHandler(MessageHandler messageHandler) {
        this.messageHandler = messageHandler;
    }

    @Autowired
    public void setCallbackQueryHandler(CallbackQueryHandler callbackQueryHandler) {
        this.callbackQueryHandler = callbackQueryHandler;
    }

    @Autowired
    public void setChatService(ChatService chatService) {
        this.chatService = chatService;
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
        return update.hasCallbackQuery()
                ? callbackQueryHandler.processCallbackQuery(update)
                : messageIsCommand(update) ? commandHandler.processMessage(update)
                : messageHandler.processMessage(update);
    }

    private boolean messageIsCommand(Update update){
        String text = update.getMessage().getText();

        return text.startsWith("/");
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
