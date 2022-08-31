package com.inventory.prosta.bot.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import com.inventory.prosta.bot.model.UpdateContext;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Update;

@Getter
@Component
@RequestScope
public class UpdateContext {
    private Update update;
    private Long chatId;
    private Chat chat;

    public void setValueFromMessage(Update update){
        this.update = update;
        this.chat = update.getMessage().getChat();
        this.chatId = update.getMessage().getChat().getId();
    }

    public void setValueFromCallbackQuery(Update update){
        this.update = update;
        this.chat = update.getCallbackQuery().getMessage().getChat();
        this.chatId = update.getCallbackQuery().getMessage().getChat().getId();
    }

}
