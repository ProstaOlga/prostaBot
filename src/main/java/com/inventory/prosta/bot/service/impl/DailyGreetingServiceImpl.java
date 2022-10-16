package com.inventory.prosta.bot.service.impl;

import com.inventory.prosta.bot.model.enums.MediaType;
import com.inventory.prosta.bot.repository.ChatRepo;
import com.inventory.prosta.bot.service.api.DailyGreetingService;
import com.inventory.prosta.bot.service.api.MediaService;
import com.inventory.prosta.bot.service.api.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import jooq.tables.pojos.ChatDb;

import java.util.List;
@Service
@Slf4j
@RequiredArgsConstructor
public class DailyGreetingServiceImpl implements DailyGreetingService {

    private final MessageService messageService;
    private final ChatRepo chatRepo;
    private final MediaService mediaService;
    @Override
    public void sendGoodMorning() {
        List<ChatDb> chats = chatRepo.getGroupChatsDailyGreetingOn();

        chats.stream()
                .map(ChatDb::getChatId)
                .forEach(chatId -> messageService.sendMediaToChat(chatId, mediaService.getRandomMediaByType(MediaType.MORNING_GREETING, chatId)));
    }

    @Override
    public void sendGoodNight() {
        List<ChatDb> chats = chatRepo.getGroupChatsDailyGreetingOn();

        chats.stream()
                .map(ChatDb::getChatId)
                .forEach(chatId -> messageService.sendMediaToChat(chatId, mediaService.getRandomMediaByType(MediaType.GOOD_NIGHT, chatId)));
    }
}
