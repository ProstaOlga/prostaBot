package com.inventory.prosta.bot.repository;

import com.inventory.prosta.bot.model.enums.MediaType;
import jooq.tables.daos.MediaChatDao;
import jooq.tables.daos.MediaDao;
import jooq.tables.pojos.Media;
import jooq.tables.pojos.MediaChat;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.DatePart;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;

import static jooq.tables.Media.MEDIA;
import static jooq.tables.MediaChat.MEDIA_CHAT;
import static org.jooq.impl.DSL.rand;

@Repository
@RequiredArgsConstructor
public class MediaRepo {

    private final DSLContext dsl;
    private MediaDao mediaDao;
    private MediaChatDao mediaChatDao;

    @PostConstruct
    void initMediaDao(){
        mediaDao = new MediaDao(dsl.configuration());
    }

    @PostConstruct
    void initMediaChatDao(){
        mediaChatDao = new MediaChatDao(dsl.configuration());
    }


    public void saveMedia(Media media){
        mediaDao.insert(media);
    }

    public Media getRandomImg(MediaType mediaType){
        return dsl.select()
                .from(MEDIA)
                .where(MEDIA.MEDIA_TYPE.eq(mediaType.toString()))
                .orderBy(rand())
                .limit(1)
                .fetchOneInto(Media.class);
    }

    public void addMediaChat(MediaChat mediaChat){
        mediaChatDao.insert(mediaChat);
    }

    public boolean existInMediaChat(MediaChat mediaChat){
        return mediaChatDao.exists(mediaChat);
    }

    public List<MediaChat> getExpiredDateMediaChat(LocalDate date){
        return dsl.select()
                .from(MEDIA_CHAT)
                .where(DSL.extract(MEDIA_CHAT.EXPIRED_AT, DatePart.MONTH).eq(date.getMonthValue()))
                .and(DSL.extract(MEDIA_CHAT.EXPIRED_AT, DatePart.DAY).eq(date.getDayOfMonth()))
                .fetchInto(MediaChat.class);
    }

    public void removeMediaChat(MediaChat mediaChat){
        mediaChatDao.delete(mediaChat);
    }

}
