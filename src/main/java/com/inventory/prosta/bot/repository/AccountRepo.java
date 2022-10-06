package com.inventory.prosta.bot.repository;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.DataType;
import org.jooq.DatePart;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;
import jooq.tables.daos.AccountDao;
import jooq.tables.pojos.Account;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import jooq.tables.daos.AccountChatDao;
import jooq.tables.pojos.AccountChat;

import static jooq.tables.Account.ACCOUNT;
import static jooq.tables.AccountChat.ACCOUNT_CHAT;

@Repository
@RequiredArgsConstructor
public class AccountRepo {

    private final DSLContext dsl;
    private AccountDao accountDao;
    private AccountChatDao accountChatDao;

    @PostConstruct
    void initAccountDao(){
        accountDao = new AccountDao(dsl.configuration());
        accountChatDao = new AccountChatDao(dsl.configuration());
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
        String dateStr = formatLocalDateToString(date);

        return dsl.select()
                .from(ACCOUNT)
                .where(ACCOUNT.BIRTHDAY.isNotNull())
                .and(DSL.extract(ACCOUNT.BIRTHDAY, DatePart.MONTH).eq(date.getMonthValue()))
                .and(DSL.extract(ACCOUNT.BIRTHDAY, DatePart.DAY).eq(date.getDayOfMonth()))
                .fetchInto(Account.class);
    }

    private String formatLocalDateToString(LocalDate localDate){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM-dd");
        return localDate.format(dateTimeFormatter);
    }


    public void joinToChat(jooq.tables.pojos.AccountChat accountChat) {
        accountChatDao.insert(accountChat);
    }

    public void leaveChat(AccountChat accountChat) {
        accountChatDao.delete(accountChat);
    }

    public AccountChat getAccountChat(Long accountId, Long chatId){
        return dsl.select()
                .from(ACCOUNT_CHAT)
                .where(ACCOUNT_CHAT.ACCOUNT_ID.eq(accountId))
                .and(ACCOUNT_CHAT.CHAT_ID.eq(chatId))
                .fetchOneInto(AccountChat.class);
    }

    public List<Account> getChatAccounts(Long chatId){
        return dsl.select(ACCOUNT.fields())
                .distinctOn(ACCOUNT.TELEGRAM_ID)
                .from(ACCOUNT)
                .innerJoin(ACCOUNT_CHAT).on(ACCOUNT_CHAT.ACCOUNT_ID.eq(ACCOUNT.TELEGRAM_ID))
                .where(ACCOUNT_CHAT.CHAT_ID.eq(chatId))
                .fetchInto(Account.class);
    }

}
