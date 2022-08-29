package com.inventory.prosta.bot.service.impl;

import com.inventory.prosta.bot.service.api.ChatService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Chat;

@Component
public class ChatServiceImpl implements ChatService {
    @Override
    public boolean checkChat(Long chatId) {
        return false;
    }

    @Override
    public void saveChat(Chat chat) {

    }

    @Override
    public void onHolidayNotice(Long chatId) {

    }

    @Override
    public void offHolidayNotice(Long chatId) {

    }

    @Override
    public void onBirthdayNotice(Long chatId) {

    }

    @Override
    public void offBirthdayNotice(Long chatId) {

    }

    @Override
    public void onDailyNotice(Long chatId) {

    }

    @Override
    public void offDailyNotice(Long chatId) {

    }
}
