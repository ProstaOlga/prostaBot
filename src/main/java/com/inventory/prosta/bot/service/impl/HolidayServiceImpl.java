package com.inventory.prosta.bot.service.impl;

import com.inventory.prosta.bot.model.enums.Holiday;
import com.inventory.prosta.bot.model.enums.MediaType;
import com.inventory.prosta.bot.service.api.ChatService;
import com.inventory.prosta.bot.service.api.HolidayService;
import com.inventory.prosta.bot.service.api.MediaService;
import com.inventory.prosta.bot.service.api.MessageService;
import com.inventory.prosta.bot.util.AccountUtils;
import com.inventory.prosta.bot.util.Symbols;
import com.inventory.prosta.bot.util.TextUtil;
import jooq.tables.pojos.Account;
import jooq.tables.pojos.ChatDb;
import jooq.tables.pojos.Media;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.MonthDay;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HolidayServiceImpl implements HolidayService {

    private final ChatService chatService;
    private final MessageService messageService;

    private final static String BIRTHDAY_CONGRATULATION_TEXT = "Поздравляю с Днем Рождения @%s!";
    private final static String BIRTHDAY_REMIND_TEXT = "Через 7 (%s) дней у @%s День Рождения!\n" +
            "Не забудтье поздравить" + Symbols.PRESENT;

    @Override
    public void congratulateUserWithBirthday(Account account) {
        List<ChatDb> accountChats = chatService.getAccountChats(account.getTelegramId()).stream()
                .filter(ChatDb::getBirthdayNotice)
                .collect(Collectors.toList());

        messageService.sendMediaToChats(MediaType.BIRTHDAY, accountChats);

        messageService.sendMessageToChats(accountChats, String.format(BIRTHDAY_CONGRATULATION_TEXT, AccountUtils.getAccountName(account)));
    }

    @Override
    public void congratulateWithTodayHolidays(List<ChatDb> chats) {
        List<Holiday> todayHolidays = Holiday.getHolidays(MonthDay.now());

        todayHolidays.forEach(holiday -> messageService.sendMediaToChats(holiday.getMediaType(), chats));
    }

    @Override
    public void birthdayReminder(Account account) {
        List<ChatDb> accountChats = chatService.getAccountChats(account.getTelegramId()).stream()
                .filter(chat -> chat.getChatType().equals("supergroup"))
                .filter(ChatDb::getBirthdayNotice)
                .collect(Collectors.toList());

        messageService.sendMessageToChats(accountChats, String.format(BIRTHDAY_REMIND_TEXT, TextUtil.getStringDay(account.getBirthday()), AccountUtils.getAccountName(account)));
    }
}

