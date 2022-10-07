package com.inventory.prosta.bot.Context;

import com.inventory.prosta.bot.model.AnswerEvent;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Getter
public class AnswerContext<T extends AnswerEvent> {

    private final Set<T> answerSet;

    public AnswerContext() {
        this.answerSet = ConcurrentHashMap.newKeySet();
    }

    public void store(T answerEvent) {
        this.answerSet.add(answerEvent);
    }

    public void remove(T answerEvent) {
        this.answerSet.remove(answerEvent);
    }

    public void removeAll() {
        this.answerSet.clear();
    }
}
