create table if not exists chat_db
(
    chat_id         bigint primary key,
    title           text,
    chat_type       text,
    holiday_notice  boolean not null,
    birthday_notice boolean not null,
    daily_notice    boolean not null
);