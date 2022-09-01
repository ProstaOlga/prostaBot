package com.inventory.prosta.bot.mapper;

import jooq.tables.pojos.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.telegram.telegrambots.meta.api.objects.User;

@Mapper
public interface AccountMapper {

    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    @Mapping(target = "telegramId", source = "id")
    @Mapping(target = "birthday", ignore = true)
    Account telegramToEntity(User user);


}
