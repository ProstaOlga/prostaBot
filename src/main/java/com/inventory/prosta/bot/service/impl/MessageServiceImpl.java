package com.inventory.prosta.bot.service.impl;

import com.inventory.prosta.bot.model.enums.MediaType;
import com.inventory.prosta.bot.service.api.MediaService;
import com.inventory.prosta.bot.service.api.MessageService;
import com.inventory.prosta.bot.telegram.TelegramMediaService;
import jooq.tables.pojos.ChatDb;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.InputFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final TelegramMediaService telegramMediaService;
    private final MediaService mediaService;

    @Override
    public void sendMessageToChats(MediaType mediaType, List<ChatDb> chats) {
        InputFile img = mediaService.getRandomImgByType(mediaType);

        chats.stream()
                .map(ChatDb::getChatId)
                .forEach(chatId -> telegramMediaService.sendImg(img, chatId));
    }
}
