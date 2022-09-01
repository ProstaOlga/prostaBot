create table if not exists account
(
    telegram_id bigint primary key,
    first_name  text,
    last_name   text,
    user_name   text not null,
    birthday    date
);