package com.inventory.prosta.bot.service.aspect;

import com.inventory.prosta.bot.mapper.AccountMapper;
import com.inventory.prosta.bot.service.api.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import jooq.tables.pojos.Account;

import java.time.LocalDate;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class UpdateAccountAspect {

    private final AccountService accountService;

    @Before("@annotation(UpdateAccountData)")
    public void updateData(JoinPoint joinPoint) {
        var user = getUser(joinPoint);

        updateAccountData(user);
    }

    private User getUser(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();

        Update update = Arrays.stream(args)
                .filter(Update.class::isInstance)
                .map(Update.class::cast)
                .findFirst()
                .orElseThrow();

        return update.getCallbackQuery().getFrom();
    }

    private void updateAccountData(User user){
        LocalDate localDate = accountService.getAccountById(user.getId()).getBirthday();
        Account account = AccountMapper.INSTANCE.telegramToEntity(user);
        account.setBirthday(localDate);

        accountService.updateAccount(account);
    }

}
