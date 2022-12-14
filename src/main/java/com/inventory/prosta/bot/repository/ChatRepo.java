package com.inventory.prosta.bot.repository;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import jooq.tables.daos.ChatDbDao;
import jooq.tables.pojos.ChatDb;
import jooq.tables.daos.AccountChatDao;

import javax.annotation.PostConstruct;
import java.util.List;

import static jooq.tables.AccountChat.ACCOUNT_CHAT;
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
    
    public boolean existChatById(Long chatId){
        return chatDao.existsById(chatId);
    }

    public List<ChatDb> getChatsHolidayOn(){
        return dsl.select()
                .from(CHAT_DB)
                .where(CHAT_DB.HOLIDAY_NOTICE.eq(Boolean.TRUE))
                .fetchInto(ChatDb.class);
    }

    public List<ChatDb> getChatsDailyGreetingOn(){
        return dsl.select()
                .from(CHAT_DB)
                .where(CHAT_DB.DAILY_NOTICE.eq(Boolean.TRUE))
                .fetchInto(ChatDb.class);
    }

    public List<ChatDb> getChatsByAccountId(Long accountId){
        return dsl.select(CHAT_DB.fields())
                .distinctOn(CHAT_DB.CHAT_ID)
                .from(CHAT_DB)
                .innerJoin(ACCOUNT_CHAT).on(ACCOUNT_CHAT.CHAT_ID.eq(CHAT_DB.CHAT_ID))
                .where(ACCOUNT_CHAT.ACCOUNT_ID.eq(accountId))
                .fetchInto(ChatDb.class);

    }

    public boolean accountExistOnChat(Long botId, Long chatId) {
        return dsl.fetchExists(dsl.selectOne()
                .from(ACCOUNT_CHAT)
                .where(ACCOUNT_CHAT.CHAT_ID.eq(chatId))
                .and(ACCOUNT_CHAT.ACCOUNT_ID.eq(botId)));
    }

}
