package com.inventory.prosta.bot.service.impl;

import com.inventory.prosta.bot.model.enums.MediaType;
import com.inventory.prosta.bot.repository.ChatRepo;
import com.inventory.prosta.bot.service.api.DailyGreetingService;
import com.inventory.prosta.bot.service.api.MediaService;
import com.inventory.prosta.bot.service.api.MessageService;
import com.inventory.prosta.bot.util.ResourceBundleUtil;
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
    private final static String TEXT_MORNING = ResourceBundleUtil.getMessageText("message.daily.morning");
    private final static String TEXT_NIGHT = ResourceBundleUtil.getMessageText("message.daily.night");
    @Override
    public void sendGoodMorning() {
        List<ChatDb> chats = chatRepo.getChatsDailyGreetingOn();

        chats.stream()
                .map(ChatDb::getChatId)
                .forEach(chatId -> messageService.sendMediaToChat(chatId, mediaService.getRandomMediaByType(MediaType.MORNING_GREETING, chatId), TEXT_MORNING));
    }

    @Override
    public void sendGoodNight() {
        List<ChatDb> chats = chatRepo.getChatsDailyGreetingOn();

        chats.stream()
                .map(ChatDb::getChatId)
                .forEach(chatId -> messageService.sendMediaToChat(chatId, mediaService.getRandomMediaByType(MediaType.GOOD_NIGHT, chatId), TEXT_NIGHT));
    }
}
