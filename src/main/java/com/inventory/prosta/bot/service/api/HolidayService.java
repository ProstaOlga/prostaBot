package com.inventory.prosta.bot.service.api;

import java.util.List;
import jooq.tables.pojos.ChatDb;

public interface HolidayService {

    void congratulateUserWithBirthday(Long accountId);

    void congratulateWithTodayHolidays(List<ChatDb> chats);
}
