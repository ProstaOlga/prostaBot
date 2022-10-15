package com.inventory.prosta.bot.service.api;

import java.util.List;
import jooq.tables.pojos.ChatDb;
import jooq.tables.pojos.Account;

public interface HolidayService {

    void congratulateUserWithBirthday(Account account);

    void congratulateWithTodayHolidays(List<ChatDb> chats);

    void birthdayReminder(Account account);
}
