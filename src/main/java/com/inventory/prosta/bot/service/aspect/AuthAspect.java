package com.inventory.prosta.bot.service.aspect;

import com.inventory.prosta.bot.service.api.AccountService;
import com.inventory.prosta.bot.service.api.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.Arrays;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class AuthAspect {


    private final ChatService chatService;
    private final AccountService accountService;


    @Before("@annotation(Auth)")
    public void authChat(JoinPoint joinPoint) {
        var chat = getChat(joinPoint);
        var account = getAccount(joinPoint);

        chatService.registerNewChat(chat);
        accountService.registerNewAccount(account);
        accountService.joinChat(account, chat);
    }


    @SneakyThrows
    private Chat getChat(JoinPoint joinPoint) {
        var update = getUpdate(joinPoint);

        return update.hasMessage() ? getChatFromMessage(update) : getChatFromCallback(update);
    }

    @SneakyThrows
    private User getAccount(JoinPoint joinPoint) {
        var update = getUpdate(joinPoint);

        return update.hasMessage() ? getUserFromMessage(update) : getUserFromCallback(update);
    }

    private Update getUpdate(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();

        return Arrays.stream(args)
                .filter(Update.class::isInstance)
                .map(Update.class::cast)
                .findFirst()
                .orElseThrow();
    }

    private User getUserFromMessage(Update update) {
        return update.getMessage().getFrom();
    }

    private Chat getChatFromMessage(Update update) {
        return update.getMessage().getChat();
    }

    private User getUserFromCallback(Update update) {
        return update.getCallbackQuery().getFrom();
    }

    private Chat getChatFromCallback(Update update) {
        return update.getCallbackQuery().getMessage().getChat();
    }
}
