package com.inventory.prosta.bot.mapper;

import jooq.tables.pojos.ChatDb;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.telegram.telegrambots.meta.api.objects.Chat;

@Mapper
public interface ChatMapper {

    ChatMapper INSTANCE = Mappers.getMapper(ChatMapper.class);

    @Mapping(target = "chatId", source = "id")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "chatType", source = "type")
    @Mapping(target = "holidayNotice", expression = "java(true)")
    @Mapping(target = "birthdayNotice", expression = "java(true)")
    @Mapping(target = "dailyNotice", expression = "java(true)")
    ChatDb telegramToEntity(Chat tgChat);

    @Mapping(target = "id", source = "chatId")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "type", source = "chatType")
    Chat entityToTelegram(ChatDb chatDb);

}
