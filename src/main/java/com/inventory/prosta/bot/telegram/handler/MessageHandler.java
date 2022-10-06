package com.inventory.prosta.bot.telegram.handler;

import com.inventory.prosta.bot.Context.AnswerContext;
import com.inventory.prosta.bot.model.AnswerEvent;
import com.inventory.prosta.bot.model.UpdateContext;
import com.inventory.prosta.bot.service.api.AnswerService;
import com.inventory.prosta.bot.service.aspect.Auth;
import com.inventory.prosta.bot.service.impl.BirthdayAnswerService;
import com.inventory.prosta.bot.util.TextParser;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Collections;

@Component
@RequiredArgsConstructor
public class MessageHandler {

    private final ApplicationContext applicationContext;
    private final UpdateContext updateContext;
    private final TextParser textParser;

//    private final AnswerContext<? super AnswerEvent> answerContext;
    private final AnswerContext answerContext;
//    private final AnswerService<? super AnswerEvent> answerService;
//    private final AnswerService<? super AnswerEvent> answerService;
    private final BirthdayAnswerService birthdayAnswerService;
    @Auth
    public BotApiMethod<?> processMessage(Update update) {
        Message message = update.getMessage();

        return message == null ? null : executeAnswerEvent(update);
    }

    private BotApiMethod<?> executeAnswerEvent(Update update){
        String message = update.getMessage().getText();
        updateContext.setValueFromMessage(update);

        answerContext.getAnswerSet().stream()
                .filter(birthdayAnswerService::eventIsAnswer)
                .forEach(birthdayAnswerService::executeAnswerEvent);

        return null;
    }
}
