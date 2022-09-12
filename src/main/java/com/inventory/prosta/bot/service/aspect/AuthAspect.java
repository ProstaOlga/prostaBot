package com.inventory.prosta.bot.service.aspect;

import com.inventory.prosta.bot.service.api.AccountService;
import com.inventory.prosta.bot.service.api.ChatService;
import com.inventory.prosta.bot.telegram.TelegramBotContext;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import com.inventory.prosta.bot.service.aspect.Auth;

import java.util.Arrays;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class AuthAspect {

    private static final String CALLBACK_QUERY = "processCallbackQuery";
    private static final String MESSAGE = "processMessage";
    private final ChatService chatService;
    private final AccountService accountService;
    private final TelegramBotContext telegramBotContext;


    @Before("@annotation(Auth)")
    public void authChat(JoinPoint joinPoint) {
        log.info("Аспект чата вызван");
        var chat = getChat(joinPoint);
        var account = getAccount(joinPoint);

        chatService.registerNewChat(chat);
//        accountService.joinChat(telegramBotContext.getBotId(), chat.getId());
        accountService.registerNewAccount(account);
        accountService.joinChat(account.getId(), chat.getId());
    }

    @Before("@annotation(AccountAuth)")
    public void authAccount(JoinPoint joinPoint) {
        log.info("Аспект пользователя вызван");
        var account = getAccount(joinPoint);

        accountService.registerNewAccount(account);
    }

    @SneakyThrows
    private Chat getChat(JoinPoint joinPoint) {
        var update = getUpdate(joinPoint);
        String methodName = joinPoint.getSignature().getName();

        switch (methodName) {
            case CALLBACK_QUERY:
                return update.getCallbackQuery().getMessage().getChat();
            case MESSAGE:
                return update.getMessage().getChat();
            default:
                throw new NotFoundException("Source method not implemented");
        }
    }

    @SneakyThrows
    private User getAccount(JoinPoint joinPoint) {
        var update = getUpdate(joinPoint);
        String methodName = joinPoint.getSignature().getName();

        switch (methodName) {
            case CALLBACK_QUERY:
                return update.getCallbackQuery().getFrom();
            case MESSAGE:
                return update.getMessage().getFrom();
            default:
                throw new NotFoundException("Source method not implemented");
        }
    }

    private Update getUpdate(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();

        return Arrays.stream(args)
                .filter(Update.class::isInstance)
                .map(Update.class::cast)
                .findFirst()
                .orElseThrow();
    }
}
