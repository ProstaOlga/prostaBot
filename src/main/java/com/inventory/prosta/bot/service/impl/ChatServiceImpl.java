package com.inventory.prosta.bot.service.impl;

import com.inventory.prosta.bot.mapper.ChatMapper;
import com.inventory.prosta.bot.repository.ChatRepo;
import com.inventory.prosta.bot.service.api.ChatService;
import jooq.tables.pojos.ChatDb;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Chat;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final ChatRepo chatRepo;

    @Override
    public boolean checkChat(Long chatId) {
        return chatRepo.checkChatById(chatId);
    }

    @Override
    public void saveChat(Chat chat) {
      ChatDb chatDb = ChatMapper.INSTANCE.telegramToDb(chat);

      chatRepo.save(chatDb);
    }

    @Override
    public void onHolidayNotice(Long chatId) {
        ChatDb chat = chatRepo.getChatById(chatId);
        chat.setHolidayNotice(true);
        chatRepo.update(chat);
    }

    @Override
    public void offHolidayNotice(Long chatId) {
        ChatDb chat = chatRepo.getChatById(chatId);
        chat.setHolidayNotice(false);
        chatRepo.update(chat);
    }

    @Override
    public void onBirthdayNotice(Long chatId) {
        ChatDb chat = chatRepo.getChatById(chatId);
        chat.setBirthdayNotice(true);
        chatRepo.update(chat);
    }

    @Override
    public void offBirthdayNotice(Long chatId) {
        ChatDb chat = chatRepo.getChatById(chatId);
        chat.setBirthdayNotice(false);
        chatRepo.update(chat);
    }

    @Override
    public void onDailyNotice(Long chatId) {
        ChatDb chat = chatRepo.getChatById(chatId);
        chat.setDailyNotice(true);
        chatRepo.update(chat);
    }

    @Override
    public void offDailyNotice(Long chatId) {
        ChatDb chat = chatRepo.getChatById(chatId);
        chat.setDailyNotice(false);
        chatRepo.update(chat);
    }

    @Override
    public void authenticateAndRegistrationNewChat(Chat chat) {
        if (!chatRepo.checkChatById(chat.getId())){
            ChatDb chatDb = ChatMapper.INSTANCE.telegramToDb(chat);
            chatRepo.save(chatDb);
        }
    }

    @Override
    public boolean isGroupChat(Long chatId) {
        return chatId < 0;
    }
}
