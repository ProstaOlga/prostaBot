package com.inventory.prosta.bot.repository;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import jooq.tables.daos.ChatDbDao;
import jooq.tables.pojos.ChatDb;

import javax.annotation.PostConstruct;

@Repository
@RequiredArgsConstructor
public class ChatRepo {

    private final DSLContext dsl;
    private ChatDbDao chatDao;

    @PostConstruct
    void initChatDao(){
        chatDao = new ChatDbDao(dsl.configuration());
    }

    public void save(ChatDb chat){
        chatDao.insert(chat);
    }

    public void update(ChatDb chat){
        chatDao.update(chat);
    }
    
    public ChatDb getChatById(Long chatId){
        return chatDao.findById(chatId);
    }
    
    public boolean checkChatById(Long chatId){
        return chatDao.existsById(chatId);
    }
    
}
