package com.inventory.prosta.bot.service.api;

import com.inventory.prosta.bot.model.answer.AnswerEvent;


public interface AnswerService<T extends AnswerEvent> {

    boolean eventIsAnswer(T answerEvent);

    void executeAnswerEvent(T answerEvent);

}
