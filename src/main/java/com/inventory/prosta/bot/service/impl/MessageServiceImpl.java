package com.inventory.prosta.bot.service.impl;

import com.inventory.prosta.bot.model.enums.MediaType;
import com.inventory.prosta.bot.service.api.AccountService;
import com.inventory.prosta.bot.service.api.ChatService;
import com.inventory.prosta.bot.service.api.MediaService;
import com.inventory.prosta.bot.service.api.MessageService;
import com.inventory.prosta.bot.telegram.TelegramBotContext;
import jooq.tables.pojos.ChatDb;
import jooq.tables.pojos.Media;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.InputFile;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MediaService mediaService;
    private final TelegramBotContext telegramBotContext;
    private final ChatService chatService;
    private final AccountService accountService;


    @Override
    @Transactional
    public void sendMediaToChats(MediaType mediaType, List<ChatDb> chats) {

        chats.stream()
                .map(ChatDb::getChatId)
                .filter(chatId -> chatService.userChatInfo(telegramBotContext.getBotId(), chatId))
                .forEach(chatId -> sendImage(mediaService.mediaToInputFile(mediaService.getRandomImgByType(mediaType)), chatId));
    }

    @Override
    public void sendMediaToChats(Media image, List<ChatDb> chats) {
        chats.stream()
                .map(ChatDb::getChatId)
                .filter(chatId -> chatService.userChatInfo(telegramBotContext.getBotId(), chatId))
                .forEach(chatId -> sendImage(mediaService.mediaToInputFile(image), chatId));
    }

    @Override
    public void sendMediaToChat(MediaType mediaType, Long chatId) {
        Media image = mediaService.getRandomImgByType(mediaType);

        sendImage(mediaService.mediaToInputFile(image), chatId);
    }

    @Override
    public void sendMediaToChat(Media media, Long chatId) {
        sendImage(mediaService.mediaToInputFile(media), chatId);
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
    public void sendMessageToChat(Long chatId, String text){
        SendMessage sendMessage = SendMessage.builder()
                .chatId(chatId)
                .text(text)
                .build();

        telegramBotContext.execute(sendMessage);
    }

    @SneakyThrows
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
}