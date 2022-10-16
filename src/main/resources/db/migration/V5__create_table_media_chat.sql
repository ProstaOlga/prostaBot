create table if not exists media_chat
(
    media_id   uuid   not null,
    chat_id    bigint not null,
    expired_at date not null,
    primary key (media_id, chat_id),

    constraint fk_exists_media
        foreign key (media_id)
            references media (id),

    constraint fk_exists_chat_db
        foreign key (chat_id)
            references chat_db (chat_id)
);