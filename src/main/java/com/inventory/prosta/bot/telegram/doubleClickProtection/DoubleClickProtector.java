package com.inventory.prosta.bot.telegram.doubleClickProtection;

import com.inventory.prosta.bot.model.UpdateContext;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@RequiredArgsConstructor
public class DoubleClickProtector {

    private final UpdateContext updateContext;
    private final PreviousCommandCallContext previousCommandCallContext;

    public boolean checkDoubleClick(){
        boolean result;

        Integer thisMessageId = updateContext.getMessageId();
        Long chatId = updateContext.getChatId();

        result = checkPreviousMessageInChat(chatId, thisMessageId);
        previousCommandCallContext.store(chatId, thisMessageId);

        return result;
    }

    private boolean checkPreviousMessageInChat(Long chatId, Integer thisMessageId) {
        if (previousCommandCallContext.getPreviousCommandCallMember().containsKey(chatId)){
            return thisMessageId.equals(previousCommandCallContext.getPreviousMessageForChat(chatId));
        }
        else {
            return false;
        }

    }


}
