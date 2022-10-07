package com.inventory.prosta.bot.Context;

import com.inventory.prosta.bot.model.AnswerEvent;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Getter
public class AnswerContext<T extends AnswerEvent> {

    private final Map<Long, T> answerMap;

    public AnswerContext() {
        this.answerMap = new ConcurrentHashMap<Long, T>();
    }

    public void store(Long accountId, T answerEvent) {
        this.answerMap.put(accountId, answerEvent);
    }

    public void removeAll(List<Long> list){
        list.forEach(answerMap::remove);
    }

    public void remove(Long accountId) {
        this.answerMap.remove(accountId);
    }
}
