/*
 * This file is generated by jOOQ.
 */
package jooq.tables.records;


import java.time.LocalDate;
import java.util.UUID;

import jooq.tables.MediaChat;

import org.jooq.Field;
import org.jooq.Record2;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class MediaChatRecord extends UpdatableRecordImpl<MediaChatRecord> implements Record3<UUID, Long, LocalDate> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.media_chat.media_id</code>.
     */
    public void setMediaId(UUID value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.media_chat.media_id</code>.
     */
    public UUID getMediaId() {
        return (UUID) get(0);
    }

    /**
     * Setter for <code>public.media_chat.chat_id</code>.
     */
    public void setChatId(Long value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.media_chat.chat_id</code>.
     */
    public Long getChatId() {
        return (Long) get(1);
    }

    /**
     * Setter for <code>public.media_chat.expired_at</code>.
     */
    public void setExpiredAt(LocalDate value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.media_chat.expired_at</code>.
     */
    public LocalDate getExpiredAt() {
        return (LocalDate) get(2);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record2<UUID, Long> key() {
        return (Record2) super.key();
    }

    // -------------------------------------------------------------------------
    // Record3 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row3<UUID, Long, LocalDate> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    @Override
    public Row3<UUID, Long, LocalDate> valuesRow() {
        return (Row3) super.valuesRow();
    }

    @Override
    public Field<UUID> field1() {
        return MediaChat.MEDIA_CHAT.MEDIA_ID;
    }

    @Override
    public Field<Long> field2() {
        return MediaChat.MEDIA_CHAT.CHAT_ID;
    }

    @Override
    public Field<LocalDate> field3() {
        return MediaChat.MEDIA_CHAT.EXPIRED_AT;
    }

    @Override
    public UUID component1() {
        return getMediaId();
    }

    @Override
    public Long component2() {
        return getChatId();
    }

    @Override
    public LocalDate component3() {
        return getExpiredAt();
    }

    @Override
    public UUID value1() {
        return getMediaId();
    }

    @Override
    public Long value2() {
        return getChatId();
    }

    @Override
    public LocalDate value3() {
        return getExpiredAt();
    }

    @Override
    public MediaChatRecord value1(UUID value) {
        setMediaId(value);
        return this;
    }

    @Override
    public MediaChatRecord value2(Long value) {
        setChatId(value);
        return this;
    }

    @Override
    public MediaChatRecord value3(LocalDate value) {
        setExpiredAt(value);
        return this;
    }

    @Override
    public MediaChatRecord values(UUID value1, Long value2, LocalDate value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached MediaChatRecord
     */
    public MediaChatRecord() {
        super(MediaChat.MEDIA_CHAT);
    }

    /**
     * Create a detached, initialised MediaChatRecord
     */
    public MediaChatRecord(UUID mediaId, Long chatId, LocalDate expiredAt) {
        super(MediaChat.MEDIA_CHAT);

        setMediaId(mediaId);
        setChatId(chatId);
        setExpiredAt(expiredAt);
    }

    /**
     * Create a detached, initialised MediaChatRecord
     */
    public MediaChatRecord(jooq.tables.pojos.MediaChat value) {
        super(MediaChat.MEDIA_CHAT);

        if (value != null) {
            setMediaId(value.getMediaId());
            setChatId(value.getChatId());
            setExpiredAt(value.getExpiredAt());
        }
    }
}