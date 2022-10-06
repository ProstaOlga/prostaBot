package com.inventory.prosta.bot.service.api;

import com.inventory.prosta.bot.model.AnswerEvent;
import org.springframework.stereotype.Service;


public interface AnswerService<E extends AnswerEvent> {

    boolean eventIsAnswer(E answerEvent);

    void executeAnswerEvent(E answerEvent);

}
