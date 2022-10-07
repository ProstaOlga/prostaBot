package com.inventory.prosta.bot.service.api;

import com.inventory.prosta.bot.model.AnswerEvent;


public interface AnswerService<T extends AnswerEvent> {

    boolean eventIsAnswer(T answerEvent);

    void executeAnswerEvent(T answerEvent);

}
