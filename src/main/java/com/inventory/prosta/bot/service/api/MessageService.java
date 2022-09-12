package com.inventory.prosta.bot.service.api;

import com.inventory.prosta.bot.model.enums.MediaType;
import jooq.tables.pojos.ChatDb;
import jooq.tables.pojos.Media;

import java.util.List;

public interface MessageService {
    void sendMediaToChats(MediaType mediaType, List<ChatDb> chats);

    void sendMediaToChat(MediaType mediaType, Long chatId);

    void deleteMessage(Long chatId, Integer messageId);

    void sendMediaToChats(Media media, List<ChatDb> chats);

    void sendMediaToChat(Media media, Long chatId);


    void sendMessageToChat(Long chatId, String text);
}
