package com.inventory.prosta.bot.service.api;

import com.inventory.prosta.bot.model.enums.MediaType;
import jooq.tables.pojos.ChatDb;

import java.util.List;

public interface MessageService {

    void sendMessageToChats(MediaType mediaType, List<ChatDb> chats);

}
