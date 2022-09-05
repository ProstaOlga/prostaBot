package com.inventory.prosta.bot.service.impl;

import com.inventory.prosta.bot.model.enums.MediaType;
import com.inventory.prosta.bot.service.api.MediaService;
import com.inventory.prosta.bot.service.api.MessageService;
import com.inventory.prosta.bot.telegram.TelegramBotContext;
import jooq.tables.pojos.ChatDb;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.InputFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MediaService mediaService;
    private final TelegramBotContext telegramBotContext;

    @Override
    public void sendMessageToChats(MediaType mediaType, List<ChatDb> chats) {
        InputFile image = mediaService.getRandomImgByType(mediaType);

        chats.stream()
                .map(ChatDb::getChatId)
                .forEach(chatId -> sendImage(image, chatId));
    }

    @Override
    public void sendMessageToChat(MediaType mediaType, Long chatId) {
        InputFile img = mediaService.getRandomImgByType(mediaType);

        sendImage(img, chatId);
    }

    @Override
    public void deleteMessageT(Long chatId, Integer messageId) {


        DeleteMessage deleteMessage = DeleteMessage.builder()
                .chatId(chatId)
                .messageId(messageId)
                .build();

        telegramBotContext.execute(deleteMessage);
    }


    private void sendImage(InputFile image, Long chatId) {
        SendPhoto sendPhoto = SendPhoto.builder()
                .photo(image)
                .chatId(chatId)
                .build();

        telegramBotContext.execute(sendPhoto);
    }


}
