package com.inventory.prosta.bot.service.impl;

import com.inventory.prosta.bot.mapper.AccountMapper;
import com.inventory.prosta.bot.repository.AccountRepo;
import com.inventory.prosta.bot.service.api.AccountService;
import com.inventory.prosta.bot.service.api.ChatService;
import jooq.tables.pojos.Account;
import jooq.tables.pojos.AccountChat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepo accountRepo;
    private final ChatService chatService;


    @Override
    public void registerNewAccount(User user) {
        if (!accountRepo.existAccountById(user.getId())) {
            var account = AccountMapper.INSTANCE.telegramToEntity(user);
            accountRepo.save(account);
        }
    }

    @Override
    public void joinChat(Long accountId, Long chatId) {
        if (!chatService.userExistOnChat(accountId, chatId)) {
            var accountChat = new AccountChat();
            accountChat.setAccountId(accountId);
            accountChat.setChatId(chatId);
            accountRepo.joinToChat(accountChat);
        }
    }

    @Override
    public void leaveChat(Long accountId, Long chatId) {
        var accountChat = accountRepo.getAccountChat(accountId, chatId);
        accountRepo.leaveChat(accountChat);
    }

    @Override
    public User getUserById(Long telegramId) {
        return null;
    }

    @Override
    public void saveUser(User user) {
    }

    @Override
    public void saveUserBirthday(Long userId, LocalDate date) {
    }

    @Override
    public void updateUserBirthday(Long userId, LocalDate date) {
    }

    @Override
    public List<Account> getAccountsWithBirthdayNow() {
        return accountRepo.getAccountsWithBirthdayNow(LocalDate.now());
    }

}
