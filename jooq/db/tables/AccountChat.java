/*
 * This file is generated by jOOQ.
 */
package db.tables;


import db.Keys;
import db.Public;
import db.tables.records.AccountChatRecord;

import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row3;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class AccountChat extends TableImpl<AccountChatRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.account_chat</code>
     */
    public static final AccountChat ACCOUNT_CHAT = new AccountChat();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<AccountChatRecord> getRecordType() {
        return AccountChatRecord.class;
    }

    /**
     * The column <code>public.account_chat.id</code>.
     */
    public final TableField<AccountChatRecord, Long> ID = createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>public.account_chat.account_id</code>.
     */
    public final TableField<AccountChatRecord, Long> ACCOUNT_ID = createField(DSL.name("account_id"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.account_chat.chat_id</code>.
     */
    public final TableField<AccountChatRecord, Long> CHAT_ID = createField(DSL.name("chat_id"), SQLDataType.BIGINT.nullable(false), this, "");

    private AccountChat(Name alias, Table<AccountChatRecord> aliased) {
        this(alias, aliased, null);
    }

    private AccountChat(Name alias, Table<AccountChatRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.account_chat</code> table reference
     */
    public AccountChat(String alias) {
        this(DSL.name(alias), ACCOUNT_CHAT);
    }

    /**
     * Create an aliased <code>public.account_chat</code> table reference
     */
    public AccountChat(Name alias) {
        this(alias, ACCOUNT_CHAT);
    }

    /**
     * Create a <code>public.account_chat</code> table reference
     */
    public AccountChat() {
        this(DSL.name("account_chat"), null);
    }

    public <O extends Record> AccountChat(Table<O> child, ForeignKey<O, AccountChatRecord> key) {
        super(child, key, ACCOUNT_CHAT);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public Identity<AccountChatRecord, Long> getIdentity() {
        return (Identity<AccountChatRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<AccountChatRecord> getPrimaryKey() {
        return Keys.ACCOUNT_CHAT_PKEY;
    }

    @Override
    public List<ForeignKey<AccountChatRecord, ?>> getReferences() {
        return Arrays.asList(Keys.ACCOUNT_CHAT__FK_EXISTS_ACCOUNT, Keys.ACCOUNT_CHAT__FK_EXISTS_CHAT);
    }

    private transient Account _account;
    private transient Chat _chat;

    /**
     * Get the implicit join path to the <code>public.account</code> table.
     */
    public Account account() {
        if (_account == null)
            _account = new Account(this, Keys.ACCOUNT_CHAT__FK_EXISTS_ACCOUNT);

        return _account;
    }

    /**
     * Get the implicit join path to the <code>public.chat</code> table.
     */
    public Chat chat() {
        if (_chat == null)
            _chat = new Chat(this, Keys.ACCOUNT_CHAT__FK_EXISTS_CHAT);

        return _chat;
    }

    @Override
    public AccountChat as(String alias) {
        return new AccountChat(DSL.name(alias), this);
    }

    @Override
    public AccountChat as(Name alias) {
        return new AccountChat(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public AccountChat rename(String name) {
        return new AccountChat(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public AccountChat rename(Name name) {
        return new AccountChat(name, null);
    }

    // -------------------------------------------------------------------------
    // Row3 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row3<Long, Long, Long> fieldsRow() {
        return (Row3) super.fieldsRow();
    }
}
