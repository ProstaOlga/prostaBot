/*
 * This file is generated by jOOQ.
 */
package db.tables;


import db.Keys;
import db.Public;
import db.tables.records.ChatRecord;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row6;
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
public class Chat extends TableImpl<ChatRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.chat</code>
     */
    public static final Chat CHAT = new Chat();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ChatRecord> getRecordType() {
        return ChatRecord.class;
    }

    /**
     * The column <code>public.chat.chat_id</code>.
     */
    public final TableField<ChatRecord, Long> CHAT_ID = createField(DSL.name("chat_id"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.chat.title</code>.
     */
    public final TableField<ChatRecord, String> TITLE = createField(DSL.name("title"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>public.chat.chat_type</code>.
     */
    public final TableField<ChatRecord, String> CHAT_TYPE = createField(DSL.name("chat_type"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>public.chat.holiday_notice</code>.
     */
    public final TableField<ChatRecord, Boolean> HOLIDAY_NOTICE = createField(DSL.name("holiday_notice"), SQLDataType.BOOLEAN.nullable(false), this, "");

    /**
     * The column <code>public.chat.birthday_notice</code>.
     */
    public final TableField<ChatRecord, Boolean> BIRTHDAY_NOTICE = createField(DSL.name("birthday_notice"), SQLDataType.BOOLEAN.nullable(false), this, "");

    /**
     * The column <code>public.chat.daily_notice</code>.
     */
    public final TableField<ChatRecord, Boolean> DAILY_NOTICE = createField(DSL.name("daily_notice"), SQLDataType.BOOLEAN.nullable(false), this, "");

    private Chat(Name alias, Table<ChatRecord> aliased) {
        this(alias, aliased, null);
    }

    private Chat(Name alias, Table<ChatRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.chat</code> table reference
     */
    public Chat(String alias) {
        this(DSL.name(alias), CHAT);
    }

    /**
     * Create an aliased <code>public.chat</code> table reference
     */
    public Chat(Name alias) {
        this(alias, CHAT);
    }

    /**
     * Create a <code>public.chat</code> table reference
     */
    public Chat() {
        this(DSL.name("chat"), null);
    }

    public <O extends Record> Chat(Table<O> child, ForeignKey<O, ChatRecord> key) {
        super(child, key, CHAT);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public UniqueKey<ChatRecord> getPrimaryKey() {
        return Keys.CHAT_PKEY;
    }

    @Override
    public Chat as(String alias) {
        return new Chat(DSL.name(alias), this);
    }

    @Override
    public Chat as(Name alias) {
        return new Chat(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Chat rename(String name) {
        return new Chat(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Chat rename(Name name) {
        return new Chat(name, null);
    }

    // -------------------------------------------------------------------------
    // Row6 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row6<Long, String, String, Boolean, Boolean, Boolean> fieldsRow() {
        return (Row6) super.fieldsRow();
    }
}
