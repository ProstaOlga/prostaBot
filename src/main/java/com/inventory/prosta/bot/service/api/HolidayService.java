package com.inventory.prosta.bot.service.api;

import com.inventory.prosta.bot.model.enums.Holiday;
import jooq.tables.pojos.Account;
import jooq.tables.pojos.ChatDb;

import java.util.List;

public interface HolidayService {

    /**
     * Отправить поздравления с Днем Рождения в чаты, в которых состоит пользователью
     *
     * @param account - entity класс, представляющий пользователя телеграм-бота.
     *                В аргументах передается account, которого нужно поздравитьт с Днем Рождения.
     */
    void congratulateUserWithBirthday(Account account);

    /**
     * Проверить, есть ли в текущую дату какой либо международжный праздник и
     * отправить поздравления с текущим праздником в список чатов, преданных в аргументе.
     *
     * @param chats - список чатов, в которые необходимо отправить поздравления с текущим праздником.
     */
    void congratulateWithTodayHolidays(Holiday holiday);

    /**
     * Отправить напоминание о Дне Рождении фккаунта в групповые чаты, в которыйх состоит переданный account.
     *
     * @param account - entity класс, представляющий пользователя телеграм-бота.
     */
    void birthdayReminder(Account account);
}
