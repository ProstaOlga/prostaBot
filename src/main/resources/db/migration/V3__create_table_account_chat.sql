create table if not exists account_chat
(
    id         bigint generated always as identity primary key,
    account_id bigint not null,
    chat_id    bigint not null,

    constraint fk_exists_account
        foreign key (account_id)
            references account (telegram_id),

    constraint fk_exists_chat
        foreign key (chat_id)
            references chat (chat_id)
);