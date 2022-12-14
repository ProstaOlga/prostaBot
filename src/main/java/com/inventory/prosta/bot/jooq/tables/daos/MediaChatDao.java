/*
 * This file is generated by jOOQ.
 */
package jooq.tables.daos;


import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import jooq.tables.MediaChat;
import jooq.tables.records.MediaChatRecord;

import org.jooq.Configuration;
import org.jooq.Record2;
import org.jooq.impl.DAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
@Repository
public class MediaChatDao extends DAOImpl<MediaChatRecord, jooq.tables.pojos.MediaChat, Record2<UUID, Long>> {

    /**
     * Create a new MediaChatDao without any configuration
     */
    public MediaChatDao() {
        super(MediaChat.MEDIA_CHAT, jooq.tables.pojos.MediaChat.class);
    }

    /**
     * Create a new MediaChatDao with an attached configuration
     */
    @Autowired
    public MediaChatDao(Configuration configuration) {
        super(MediaChat.MEDIA_CHAT, jooq.tables.pojos.MediaChat.class, configuration);
    }

    @Override
    public Record2<UUID, Long> getId(jooq.tables.pojos.MediaChat object) {
        return compositeKeyRecord(object.getMediaId(), object.getChatId());
    }

    /**
     * Fetch records that have <code>media_id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<jooq.tables.pojos.MediaChat> fetchRangeOfMediaId(UUID lowerInclusive, UUID upperInclusive) {
        return fetchRange(MediaChat.MEDIA_CHAT.MEDIA_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>media_id IN (values)</code>
     */
    public List<jooq.tables.pojos.MediaChat> fetchByMediaId(UUID... values) {
        return fetch(MediaChat.MEDIA_CHAT.MEDIA_ID, values);
    }

    /**
     * Fetch records that have <code>chat_id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<jooq.tables.pojos.MediaChat> fetchRangeOfChatId(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(MediaChat.MEDIA_CHAT.CHAT_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>chat_id IN (values)</code>
     */
    public List<jooq.tables.pojos.MediaChat> fetchByChatId(Long... values) {
        return fetch(MediaChat.MEDIA_CHAT.CHAT_ID, values);
    }

    /**
     * Fetch records that have <code>expired_at BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<jooq.tables.pojos.MediaChat> fetchRangeOfExpiredAt(LocalDate lowerInclusive, LocalDate upperInclusive) {
        return fetchRange(MediaChat.MEDIA_CHAT.EXPIRED_AT, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>expired_at IN (values)</code>
     */
    public List<jooq.tables.pojos.MediaChat> fetchByExpiredAt(LocalDate... values) {
        return fetch(MediaChat.MEDIA_CHAT.EXPIRED_AT, values);
    }
}
