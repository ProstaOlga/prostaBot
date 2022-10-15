package com.inventory.prosta.bot.telegram.handler;

import com.inventory.prosta.bot.Context.AnswerContext;
import com.inventory.prosta.bot.model.answer.AnswerEvent;
import com.inventory.prosta.bot.model.UpdateContext;
import com.inventory.prosta.bot.service.api.AnswerService;
import com.inventory.prosta.bot.service.aspect.Auth;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class AnswerHandler<T extends AnswerEvent> {

    private final UpdateContext updateContext;
    private final AnswerContext<T> answerContext;
    private final List<AnswerService<T>> answerServices;

    @Auth
    public BotApiMethod<?> processMessage(Update update) {
        Message message = update.getMessage();

        return message == null
                ? null
                : executeAnswerEvent(update);
    }

    private BotApiMethod<?> executeAnswerEvent(Update update) {
        String message = update.getMessage().getText();

        updateContext.setValueFromMessage(update);
        answerContext.getAnswerMap().forEach((key, value) -> this.execute(value));

        return null;
    }

    private void execute(T answerEvent) {
        log.info("User id : {}, name : {}, executed new answerEvent in chat {}",
                updateContext.getUserId(),
                updateContext.getUpdate().getMessage().getFrom().getUserName(),
                updateContext.getChatId());

        var service = getAnswerService(answerEvent);
        Optional.of(answerEvent).stream()
                .filter(service::eventIsAnswer)
                .forEach(service::executeAnswerEvent);
    }

    private AnswerService<T> getAnswerService(T answerEvent) {
        ResolvableType type = ResolvableType.forClassWithGenerics(AnswerService.class, answerEvent.getClass());

        return answerServices.stream()
                .filter(type::isInstance)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Implement handler for " + answerEvent.getClass().getSimpleName()));
    }

}
