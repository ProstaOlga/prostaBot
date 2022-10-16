package com.inventory.prosta.bot.mapper;

import com.inventory.prosta.bot.util.AccountUtils;
import jooq.tables.pojos.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.telegram.telegrambots.meta.api.objects.User;

@Mapper
public interface AccountMapper {

    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    @Mapping(target = "telegramId", source = "id")
    @Mapping(target = "birthday", ignore = true)
    @Mapping(target = "userName", source = "user", qualifiedByName = "defaultByEmptyUsername")
    Account telegramToEntity(User user);

    @Named("defaultByEmptyUsername")
    default String defaultByEmptyUsername(User user) {
        return AccountUtils.getAccountName(user);
    }

}
