/*
 * This file is generated by jOOQ.
 */
package db.tables.records;


import db.tables.AccountChat;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class AccountChatRecord extends UpdatableRecordImpl<AccountChatRecord> implements Record3<Long, Long, Long> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.account_chat.id</code>.
     */
    public void setId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.account_chat.id</code>.
     */
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>public.account_chat.account_id</code>.
     */
    public void setAccountId(Long value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.account_chat.account_id</code>.
     */
    public Long getAccountId() {
        return (Long) get(1);
    }

    /**
     * Setter for <code>public.account_chat.chat_id</code>.
     */
    public void setChatId(Long value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.account_chat.chat_id</code>.
     */
    public Long getChatId() {
        return (Long) get(2);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record3 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row3<Long, Long, Long> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    @Override
    public Row3<Long, Long, Long> valuesRow() {
        return (Row3) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return AccountChat.ACCOUNT_CHAT.ID;
    }

    @Override
    public Field<Long> field2() {
        return AccountChat.ACCOUNT_CHAT.ACCOUNT_ID;
    }

    @Override
    public Field<Long> field3() {
        return AccountChat.ACCOUNT_CHAT.CHAT_ID;
    }

    @Override
    public Long component1() {
        return getId();
    }

    @Override
    public Long component2() {
        return getAccountId();
    }

    @Override
    public Long component3() {
        return getChatId();
    }

    @Override
    public Long value1() {
        return getId();
    }

    @Override
    public Long value2() {
        return getAccountId();
    }

    @Override
    public Long value3() {
        return getChatId();
    }

    @Override
    public AccountChatRecord value1(Long value) {
        setId(value);
        return this;
    }

    @Override
    public AccountChatRecord value2(Long value) {
        setAccountId(value);
        return this;
    }

    @Override
    public AccountChatRecord value3(Long value) {
        setChatId(value);
        return this;
    }

    @Override
    public AccountChatRecord values(Long value1, Long value2, Long value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached AccountChatRecord
     */
    public AccountChatRecord() {
        super(AccountChat.ACCOUNT_CHAT);
    }

    /**
     * Create a detached, initialised AccountChatRecord
     */
    public AccountChatRecord(Long id, Long accountId, Long chatId) {
        super(AccountChat.ACCOUNT_CHAT);

        setId(id);
        setAccountId(accountId);
        setChatId(chatId);
    }

    /**
     * Create a detached, initialised AccountChatRecord
     */
    public AccountChatRecord(db.tables.pojos.AccountChat value) {
        super(AccountChat.ACCOUNT_CHAT);

        if (value != null) {
            setId(value.getId());
            setAccountId(value.getAccountId());
            setChatId(value.getChatId());
        }
    }
}
