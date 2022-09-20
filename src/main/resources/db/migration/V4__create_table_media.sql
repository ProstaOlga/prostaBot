create table if not exists media
(
    id         uuid primary key default uuid_generate_v4(),
    media      bytea,
    media_type text,
    media_format text
);