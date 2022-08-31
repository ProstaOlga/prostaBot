package com.inventory.prosta.bot.repository;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import jooq.tables.daos.ChatDbDao;
import jooq.tables.pojos.ChatDb;

import javax.annotation.PostConstruct;
import java.util.List;

import static jooq.tables.ChatDb.CHAT_DB;

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

    public List<ChatDb> getGroupChatsHolidayOn(){
        return dsl.select()
                .from(CHAT_DB)
                .where(CHAT_DB.CHAT_TYPE.eq("supergroup"))
                .and(CHAT_DB.HOLIDAY_NOTICE.eq(Boolean.TRUE))
                .fetchInto(ChatDb.class);
    }

    public List<ChatDb> getGroupChatsBirthdayOn(){
        return dsl.select()
                .from(CHAT_DB)
                .where(CHAT_DB.CHAT_TYPE.eq("supergroup"))
                .and(CHAT_DB.BIRTHDAY_NOTICE.eq(Boolean.TRUE))
                .fetchInto(ChatDb.class);
    }

    public List<ChatDb> getGroupChatsDailyGreetingOn(){
        return dsl.select()
                .from(CHAT_DB)
                .where(CHAT_DB.CHAT_TYPE.eq("supergroup"))
                .and(CHAT_DB.DAILY_NOTICE.eq(Boolean.TRUE))
                .fetchInto(ChatDb.class);
    }
    
}
