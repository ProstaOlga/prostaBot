package com.inventory.prosta.bot.Context;

import com.inventory.prosta.bot.model.answer.AnswerEvent;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Getter
@Slf4j
public class AnswerContext<T extends AnswerEvent> {

    private final Map<Long, T> answerMap;

    public AnswerContext() {
        this.answerMap = new ConcurrentHashMap<>();
    }

    public void store(Long accountId, T answerEvent) {
        this.answerMap.put(accountId, answerEvent);

        log.debug("Answer event store to List. Key: {}, AnswerEvent type^ {}", accountId, answerEvent.getClass().getSimpleName());
    }

    public void removeAll(List<Long> list){
        list.forEach(answerMap::remove);
    }

    public void remove(Long accountId) {
        this.answerMap.remove(accountId);
    }
}
