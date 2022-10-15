/*
 * This file is generated by jOOQ.
 */
package jooq.tables.pojos;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class MediaChat implements Serializable {

    private static final long serialVersionUID = 1L;

    private UUID      mediaId;
    private Long      chatId;
    private LocalDate expiredAt;

    public MediaChat() {}

    public MediaChat(MediaChat value) {
        this.mediaId = value.mediaId;
        this.chatId = value.chatId;
        this.expiredAt = value.expiredAt;
    }

    public MediaChat(
        UUID      mediaId,
        Long      chatId,
        LocalDate expiredAt
    ) {
        this.mediaId = mediaId;
        this.chatId = chatId;
        this.expiredAt = expiredAt;
    }

    /**
     * Getter for <code>public.media_chat.media_id</code>.
     */
    public UUID getMediaId() {
        return this.mediaId;
    }

    /**
     * Setter for <code>public.media_chat.media_id</code>.
     */
    public void setMediaId(UUID mediaId) {
        this.mediaId = mediaId;
    }

    /**
     * Getter for <code>public.media_chat.chat_id</code>.
     */
    public Long getChatId() {
        return this.chatId;
    }

    /**
     * Setter for <code>public.media_chat.chat_id</code>.
     */
    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    /**
     * Getter for <code>public.media_chat.expired_at</code>.
     */
    public LocalDate getExpiredAt() {
        return this.expiredAt;
    }

    /**
     * Setter for <code>public.media_chat.expired_at</code>.
     */
    public void setExpiredAt(LocalDate expiredAt) {
        this.expiredAt = expiredAt;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("MediaChat (");

        sb.append(mediaId);
        sb.append(", ").append(chatId);
        sb.append(", ").append(expiredAt);

        sb.append(")");
        return sb.toString();
    }
}
