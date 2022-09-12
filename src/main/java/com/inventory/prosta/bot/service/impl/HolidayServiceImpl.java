package com.inventory.prosta.bot.service.impl;

import com.inventory.prosta.bot.model.enums.Holiday;
import com.inventory.prosta.bot.model.enums.MediaType;
import com.inventory.prosta.bot.service.api.ChatService;
import com.inventory.prosta.bot.service.api.HolidayService;
import com.inventory.prosta.bot.service.api.MediaService;
import com.inventory.prosta.bot.service.api.MessageService;
import jooq.tables.pojos.ChatDb;
import jooq.tables.pojos.Media;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.MonthDay;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HolidayServiceImpl implements HolidayService {

    private final ChatService chatService;
    private final MessageService messageService;
    private final MediaService mediaService;

    @Override
    public void congratulateUserWithBirthday(Long accountId) {
        List<ChatDb> accountChats = chatService.getAccountChats(accountId);

        Media image = mediaService.getRandomImgByType(MediaType.BIRTHDAY);

        messageService.sendMediaToChats(image, accountChats);
    }

    @Override
    public void congratulateWithTodayHolidays(List<ChatDb> chats) {
        List<Holiday> todayHolidays = Holiday.getHolidays(MonthDay.now());

        todayHolidays.forEach(holiday -> messageService.sendMediaToChats(holiday.getMediaType(), chats));
    }
}

