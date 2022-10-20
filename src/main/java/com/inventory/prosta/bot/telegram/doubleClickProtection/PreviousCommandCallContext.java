package com.inventory.prosta.bot.telegram.doubleClickProtection;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Getter
public class PreviousCommandCallContext {
    private final Map<Long, Integer> previousCommandCallMember;

    public PreviousCommandCallContext(){
        this.previousCommandCallMember = new ConcurrentHashMap<>();
    }

    public void store(Long chatId, Integer previousMessageId){
        this.previousCommandCallMember.put(chatId, previousMessageId);
    }

    public Integer getPreviousMessageForChat(Long chatId){
        return this.previousCommandCallMember.get(chatId);
    }
}
