package com.inventory.prosta.bot.service.impl;

import com.inventory.prosta.bot.model.answer.AnswerEvent;
import com.inventory.prosta.bot.model.CheckAnswer;
import com.inventory.prosta.bot.model.UpdateContext;
import com.inventory.prosta.bot.service.api.AnswerService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.objects.Update;

public abstract class AbstractAnswerService<T extends AnswerEvent> implements AnswerService<T> {

    @Setter
    @Autowired
    protected UpdateContext updateContext;

    protected final boolean conditionsCheck(Update update, T answerEvent) {
        CheckAnswer checkAnswer = CheckAnswer.builder()
                .chatCheck(chatIdCheck(answerEvent))
                .userCheck(UserIdCheck(update, answerEvent))
                .logicCheck(logicCheck())
                .build();

        if (!checkAnswer.isLogicCheck()) {
            errorMessage(answerEvent);
        }

        return checkAnswer.getCheckingResult();
    }

    protected abstract boolean chatIdCheck(T answerEvent);

    protected abstract boolean UserIdCheck(Update update, T answerEvent);

    protected abstract boolean logicCheck();

    protected abstract void errorMessage(T answerEvent);

    protected abstract void successMessage(String accountName, String date);
}
