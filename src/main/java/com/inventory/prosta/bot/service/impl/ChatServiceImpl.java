package com.inventory.prosta.bot.service.impl;

import com.inventory.prosta.bot.mapper.ChatMapper;
import com.inventory.prosta.bot.model.UpdateContext;
import com.inventory.prosta.bot.repository.ChatRepo;
import com.inventory.prosta.bot.service.api.ChatService;
import com.inventory.prosta.bot.telegram.TelegramBot;
import com.inventory.prosta.bot.telegram.TelegramBotContext;
import jooq.tables.pojos.ChatDb;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.groupadministration.GetChatMember;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import jooq.tables.pojos.Account;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final ChatRepo chatRepo;
    private final TelegramBotContext telegramBotContext;
    private final UpdateContext updateContext;
    private final TelegramBot telegramBot;


    @Override
    public List<ChatDb> getAccountChats(Long accountId) {
        return chatRepo.getChatsByAccountId(accountId);
    }

    @Override
    public boolean checkChat(Long chatId) {
        return chatRepo.existChatById(chatId);
    }

    @Override
    public void saveChat(Chat chat) {
      ChatDb chatDb = ChatMapper.INSTANCE.telegramToEntity(chat);

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
    public void registerNewChat(Chat chat) {
        if (!chatRepo.existChatById(chat.getId())){
            var chatDb = ChatMapper.INSTANCE.telegramToEntity(chat);
            chatRepo.save(chatDb);
        }
    }

    @Override
    public boolean isGroupChat(Long chatId) {
        return chatRepo.getChatById(chatId).getChatType().equals("GROUPCHATTYPE");
    }

    @Override
    public boolean isBirthdayNoticeOn(Long chatId) {
        ChatDb chat = chatRepo.getChatById(chatId);

        return chat.getBirthdayNotice();
    }

    @Override
    public boolean isHolidayNoticeOn(Long chatId) {
        ChatDb chat = chatRepo.getChatById(chatId);

        return chat.getHolidayNotice();
    }

    @Override
    public boolean isDailyNoticeOn(Long chatId) {
        ChatDb chat = chatRepo.getChatById(chatId);

        return chat.getDailyNotice();
    }

    @Override
    public boolean userExistOnChat(Long accountId, Long chatId) {
        return chatRepo.accountExistOnChat(accountId, chatId);
    }

    @SneakyThrows
    public boolean userChatInfo(Long userId, Long chatId) {
        GetChatMember chatMember = GetChatMember.builder()
                .chatId(chatId)
                .userId(userId)
                .build();
        try {
            var member = telegramBot.execute(chatMember);
        }
        catch (TelegramApiRequestException e){
            return false;
        }

        return true;
    }

}
