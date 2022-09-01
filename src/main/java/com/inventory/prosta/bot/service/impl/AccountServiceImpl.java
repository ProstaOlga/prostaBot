package com.inventory.prosta.bot.service.impl;

import com.inventory.prosta.bot.mapper.AccountMapper;
import com.inventory.prosta.bot.repository.AccountRepo;
import com.inventory.prosta.bot.service.api.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.User;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepo accountRepo;


    @Override
    public void registerNewAccount(User user) {
        if (!accountRepo.existAccountById(user.getId())){
            var account = AccountMapper.INSTANCE.telegramToEntity(user);
            accountRepo.save(account);
        }
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
}
