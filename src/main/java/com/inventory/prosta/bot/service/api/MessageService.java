package com.inventory.prosta.bot.service.api;

import jooq.tables.pojos.Media;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface MessageService {

//    void sendMediaToChats(MediaType mediaType, List<ChatDb> chats);

    /**
     * Отправить Media в чат с переданым в аргументе chatId.
     *
     * @param chatId - id telegram-чата в который необходимо отправить сообщение.
     * @param media  - media, который необходимо отправить в чат.
     */
    void sendMediaToChat(Long chatId, Media media);

    void sendMediaToChat(Long chatId, Media media, String text);

    /**
     * Отправить текстовое сообщение в чат.
     *
     * @param chatId - id telegram-чата в который необходимо отправить сообщение.
     * @param text   - текст сообщения.
     */
    void sendMessageToChat(Long chatId, String text);


    /**
     * Удалить определенное сообщение из чата
     *
     * @param chatId - id telegram-чата в который необходимо отправить сообщение.
     * @param messageId - id сообщения в чате, которое необходимо удалить.
     */
    void deleteMessage(Long chatId, Integer messageId);

    /**
     * Отправить сообщение в чат.
     * @param sendMessage - класс TelegramBotAPI, который служит для отправления сообщения в чат.
     */
    void sendMessageToChat(SendMessage sendMessage);

}
