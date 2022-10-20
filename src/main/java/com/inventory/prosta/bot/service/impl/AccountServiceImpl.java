package com.inventory.prosta.bot.service.impl;

import com.inventory.prosta.bot.mapper.AccountMapper;
import com.inventory.prosta.bot.repository.AccountRepo;
import com.inventory.prosta.bot.service.api.AccountService;
import com.inventory.prosta.bot.service.api.ChatService;
import com.inventory.prosta.bot.telegram.TelegramBotContext;
import jooq.tables.pojos.Account;
import jooq.tables.pojos.AccountChat;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.User;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final AccountRepo accountRepo;
    private final ChatService chatService;
    private final TelegramBotContext telegramBotContext;

    @Override
    public void registerNewAccount(User user) {
        if (!accountRepo.existAccountById(user.getId())) {
            var account = AccountMapper.INSTANCE.telegramToEntity(user);
            accountRepo.save(account);
        }
    }

    @Override
    public void joinChat(Long accountId, Long chatId) {
        if (!accountId.equals(telegramBotContext.getGroupBotId()) && !chatService.userExistOnChat(accountId, chatId )) {
            var accountChat = new AccountChat();
            accountChat.setAccountId(accountId);
            accountChat.setChatId(chatId);
            accountRepo.joinToChat(accountChat);
        }
    }

    @Override
    public List<Account> getChatAccounts(Long chatId) {
        return accountRepo.getChatAccounts(chatId);
    }

    @Override
    public Account getAccountById(Long telegramId) {
        return accountRepo.getAccountById(telegramId);
    }


    @Override
    public void updateAccount(Account account) {
        accountRepo.update(account);

        log.debug("Account: {} is updated", account.toString());
    }

    @Override
    public List<Account> getAccountsWithBirthdayNow(LocalDate localDate) {
        return accountRepo.getAccountsWithBirthdayNow(localDate);
    }

}
