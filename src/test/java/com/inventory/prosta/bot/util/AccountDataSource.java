package com.inventory.prosta.bot.util;

import jooq.tables.pojos.Account;
import net.bytebuddy.utility.RandomString;
import org.apache.commons.lang3.RandomUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AccountDataSource implements DataSource<Account>{

    @Override
    public List<Account> getList(Account instance, int size) {
        List<Account> result = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            Account account = new Account();
            account.setTelegramId(RandomUtils.nextLong());
            account.setUserName(instance.getUserName());
            account.setFirstName(instance.getFirstName());
            account.setLastName(instance.getLastName());
            account.setBirthday(instance.getBirthday());
            result.add(account);
        }

        return result;
    }

    @Override
    public Account getSingleInstance() {
        Account account = new Account();
        account.setTelegramId(RandomUtils.nextLong());
        account.setUserName(RandomString.make(16));
        account.setFirstName(RandomString.make(16));
        account.setLastName(RandomString.make(16));
        account.setBirthday(LocalDate.now());

        return account;
    }
}
