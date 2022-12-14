/*
 * This file is generated by jOOQ.
 */
package jooq.tables;


import java.time.LocalDate;

import jooq.Keys;
import jooq.Public;
import jooq.tables.records.AccountRecord;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row5;
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
public class Account extends TableImpl<AccountRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.account</code>
     */
    public static final Account ACCOUNT = new Account();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<AccountRecord> getRecordType() {
        return AccountRecord.class;
    }

    /**
     * The column <code>public.account.telegram_id</code>.
     */
    public final TableField<AccountRecord, Long> TELEGRAM_ID = createField(DSL.name("telegram_id"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.account.first_name</code>.
     */
    public final TableField<AccountRecord, String> FIRST_NAME = createField(DSL.name("first_name"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>public.account.last_name</code>.
     */
    public final TableField<AccountRecord, String> LAST_NAME = createField(DSL.name("last_name"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>public.account.user_name</code>.
     */
    public final TableField<AccountRecord, String> USER_NAME = createField(DSL.name("user_name"), SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>public.account.birthday</code>.
     */
    public final TableField<AccountRecord, LocalDate> BIRTHDAY = createField(DSL.name("birthday"), SQLDataType.LOCALDATE, this, "");

    private Account(Name alias, Table<AccountRecord> aliased) {
        this(alias, aliased, null);
    }

    private Account(Name alias, Table<AccountRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.account</code> table reference
     */
    public Account(String alias) {
        this(DSL.name(alias), ACCOUNT);
    }

    /**
     * Create an aliased <code>public.account</code> table reference
     */
    public Account(Name alias) {
        this(alias, ACCOUNT);
    }

    /**
     * Create a <code>public.account</code> table reference
     */
    public Account() {
        this(DSL.name("account"), null);
    }

    public <O extends Record> Account(Table<O> child, ForeignKey<O, AccountRecord> key) {
        super(child, key, ACCOUNT);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public UniqueKey<AccountRecord> getPrimaryKey() {
        return Keys.ACCOUNT_PKEY;
    }

    @Override
    public Account as(String alias) {
        return new Account(DSL.name(alias), this);
    }

    @Override
    public Account as(Name alias) {
        return new Account(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Account rename(String name) {
        return new Account(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Account rename(Name name) {
        return new Account(name, null);
    }

    // -------------------------------------------------------------------------
    // Row5 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row5<Long, String, String, String, LocalDate> fieldsRow() {
        return (Row5) super.fieldsRow();
    }
}
