package com.inventory.prosta.bot.repository;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import jooq.tables.daos.AccountDao;
import jooq.tables.pojos.Account;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.List;

import static jooq.tables.Account.ACCOUNT;

@Repository
@RequiredArgsConstructor
public class AccountRepo {

    private final DSLContext dsl;
    private AccountDao accountDao;

    @PostConstruct
    void initAccountDao(){
        accountDao = new AccountDao(dsl.configuration());
    }

    public void save(Account account){
        accountDao.insert(account);
    }

    public void update(Account account){
        accountDao.update(account);
    }

    public Account getAccountById(Long id){
        return accountDao.findById(id);
    }

    public boolean existAccountById(Long id){
        return accountDao.existsById(id);
    }

    public List<Account> getAccountsWithBirthdayNow(LocalDate date){
        return dsl.select()
                .from(ACCOUNT)
                .where(ACCOUNT.BIRTHDAY.isNotNull())
                .and(ACCOUNT.BIRTHDAY.eq(date))
                .fetchInto(Account.class);
    }


}
