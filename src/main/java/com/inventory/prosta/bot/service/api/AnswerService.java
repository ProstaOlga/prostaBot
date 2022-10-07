package com.inventory.prosta.bot.service.api;

import com.inventory.prosta.bot.model.AnswerEvent;


public interface AnswerService<E extends AnswerEvent> {

    boolean eventIsAnswer(E answerEvent);

    void executeAnswerEvent(E answerEvent);

}
