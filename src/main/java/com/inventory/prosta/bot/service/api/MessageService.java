package com.inventory.prosta.bot.service.api;

import com.inventory.prosta.bot.model.enums.MediaType;
import jooq.tables.pojos.ChatDb;
import jooq.tables.pojos.Media;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

import java.util.List;

public interface MessageService {
    void sendMediaToChats(MediaType mediaType, List<ChatDb> chats);

    void sendMediaToChat(Media media, Long chatId);

    void sendMediaKeyboardToChat(SendPhoto sendPhoto);

    void sendMessageToChat(Long chatId, String text);

    void deleteMessage(Long chatId, Integer messageId);

    void sendMessageToChats(List<ChatDb> chats, String text);

    void sendMessageToChat(SendMessage sendMessage);

    void editMessage(EditMessageText editMessageText);
}
