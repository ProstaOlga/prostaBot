package com.inventory.prosta.bot.service.impl;

import com.inventory.prosta.bot.model.enums.MediaFormat;
import com.inventory.prosta.bot.model.enums.MediaType;
import com.inventory.prosta.bot.service.api.ChatService;
import com.inventory.prosta.bot.service.api.MediaService;
import com.inventory.prosta.bot.service.api.MessageService;
import com.inventory.prosta.bot.telegram.TelegramBotContext;
import jooq.tables.pojos.ChatDb;
import jooq.tables.pojos.Media;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.send.SendVideo;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.InputFile;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MediaService mediaService;
    private final TelegramBotContext telegramBotContext;
    private final ChatService chatService;

//    @Override
//    @Transactional
//    public void sendMediaToChats(MediaType mediaType, List<ChatDb> chats) {
//        chats.stream()
//                .map(ChatDb::getChatId)
//                .filter(chatId -> chatService.userChatInfo(telegramBotContext.getBotId(), chatId))
//                .forEach(chatId -> getRandomMediaAndSendToChat(mediaType, chatId));
//    }
//
//    private void getRandomMediaAndSendToChat(MediaType mediaType, Long chatId) {
//        Media media = mediaService.getRandomMediaByType(mediaType, chatId);
//        sendImage(mediaService.mediaToInputFile(media), chatId);
//
//        mediaService.addToMediaChatTable(media.getId(), chatId, LocalDate.now());
//    }


    @Override
    public void sendMediaToChat(Long chatId, Media media) {
        if (MediaFormat.isVideo(media.getMediaFormat())) {
            sendVideo(mediaService.mediaToInputFile(media), chatId);
            mediaService.addToMediaChatTable(media.getId(), chatId, LocalDate.now());
        } else {
            sendImage(mediaService.mediaToInputFile(media), chatId);
            mediaService.addToMediaChatTable(media.getId(), chatId, LocalDate.now());
        }
    }

    @Override
    public void sendMessageToChat(Long chatId, String text) {
        SendMessage sendMessage = SendMessage.builder()
                .chatId(chatId)
                .text(text)
                .build();

        telegramBotContext.execute(sendMessage);
    }

    @Override
    public void deleteMessage(Long chatId, Integer messageId) {
        DeleteMessage deleteMessage = DeleteMessage.builder()
                .chatId(chatId)
                .messageId(messageId)
                .build();

        telegramBotContext.execute(deleteMessage);
    }

    @Override
    public void sendMessageToChat(SendMessage sendMessage) {
        telegramBotContext.execute(sendMessage);
    }

    private void sendImage(InputFile image, Long chatId) {
        SendPhoto sendPhoto = SendPhoto.builder()
                .photo(image)
                .chatId(chatId)
                .build();
        try {
            telegramBotContext.execute(sendPhoto);
        } catch (Exception e) {
            log.warn(e.getMessage());
        }
    }

    private void sendVideo(InputFile image, Long chatId) {
        SendVideo sendVideo = SendVideo.builder()
                .video(image)
                .chatId(chatId)
                .build();
        try {
            telegramBotContext.execute(sendVideo);
        } catch (Exception e) {
            log.warn(e.getMessage());
        }
    }
}