package com.inventory.prosta.bot.Context;

import com.inventory.prosta.bot.model.AnswerEvent;
import com.inventory.prosta.bot.model.BirthdayAnswerEvent;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Getter
public class AnswerContext {

    private final Set<BirthdayAnswerEvent> answerSet;

    public AnswerContext(){
        this.answerSet = ConcurrentHashMap.newKeySet();
    }

    public void store(BirthdayAnswerEvent answerEvent){
        this.answerSet.add(answerEvent);
    }

    public void remove(BirthdayAnswerEvent answerEvent){
        this.answerSet.remove(answerEvent);
    }

    public void removeAll(){
        this.answerSet.clear();
    }
}
