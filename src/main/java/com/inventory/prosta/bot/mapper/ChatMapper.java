package com.inventory.prosta.bot.mapper;

import com.inventory.prosta.bot.util.AccountUtils;
import jooq.tables.pojos.ChatDb;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;

@Mapper
public interface ChatMapper {

    ChatMapper INSTANCE = Mappers.getMapper(ChatMapper.class);

    @Mapping(target = "chatId", source = "id")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "chatType", source = "type")
    @Mapping(target = "birthdayNotice", expression = "java(true)")
    @Mapping(target = "holidayNotice", source = "tgChat", qualifiedByName = "defaultValueHolidayNotice")
    @Mapping(target = "dailyNotice", source = "tgChat", qualifiedByName = "defaultValueDailyNotice")
    ChatDb telegramToEntity(Chat tgChat);

    @Mapping(target = "id", source = "chatId")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "type", source = "chatType")
    Chat entityToTelegram(ChatDb chatDb);

    @Named("defaultValueHolidayNotice")
    default boolean defaultValueHolidayNotice(Chat tgChat) {
        return tgChat.isGroupChat() || tgChat.isSuperGroupChat() ? true : false;
    }

    @Named("defaultValueDailyNotice")
    default boolean defaultValueDailyNotice(Chat tgChat) {
        return tgChat.isGroupChat() || tgChat.isSuperGroupChat() ? true : false;
    }

}
