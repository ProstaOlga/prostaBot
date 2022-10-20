package com.inventory.prosta.bot.service.impl;

import com.inventory.prosta.bot.model.enums.Holiday;
import com.inventory.prosta.bot.model.enums.MediaType;
import com.inventory.prosta.bot.service.api.ChatService;
import com.inventory.prosta.bot.service.api.HolidayService;
import com.inventory.prosta.bot.service.api.MediaService;
import com.inventory.prosta.bot.service.api.MessageService;
import com.inventory.prosta.bot.util.AccountUtils;
import com.inventory.prosta.bot.util.ResourceBundleUtil;
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

    private final MediaService mediaService;

    private final static String BIRTHDAY_CONGRATULATION_TEXT = ResourceBundleUtil.getMessageText("message.birthday.congratulation");
    private final static String BIRTHDAY_REMIND_TEXT = ResourceBundleUtil.getMessageText("message.birthday.remind") + Symbols.PRESENT;

    @Override
    public void congratulateUserWithBirthday(Account account) {
        String text = String.format(BIRTHDAY_CONGRATULATION_TEXT, AccountUtils.getAccountName(account));

        List<ChatDb> accountChats = chatService.getAccountChats(account.getTelegramId()).stream()
                .filter(ChatDb::getBirthdayNotice)
                .collect(Collectors.toList());

        accountChats.stream()
                .map(ChatDb::getChatId)
                .forEach(chatId -> messageService.sendMediaToChat(chatId, mediaService.getRandomMediaByType(MediaType.BIRTHDAY, chatId)));

//        messageService.sendMediaToChats(MediaType.BIRTHDAY, accountChats);

        accountChats.stream()
                .map(ChatDb::getChatId)
                .forEach(chatId -> messageService.sendMessageToChat(chatId, text));
    }

    @Override
    public void birthdayReminder(Account account) {
        String text =  String.format(BIRTHDAY_REMIND_TEXT, TextUtil.getStringDay(account.getBirthday()), AccountUtils.getAccountName(account));
        List<ChatDb> accountChats = chatService.getAccountChats(account.getTelegramId()).stream()
                .filter(chat -> chat.getChatType().equals("supergroup") || chat.getChatType().equals("group"))
                .filter(ChatDb::getBirthdayNotice)
                .collect(Collectors.toList());

        accountChats.stream()
                .map(ChatDb::getChatId)
                .forEach(chatId -> messageService.sendMessageToChat(chatId, text));
    }

    @Override
    public void congratulateWithTodayHolidays(List<ChatDb> chats) {
        List<Holiday> todayHolidays = Holiday.getHolidays(MonthDay.now());

        todayHolidays.forEach(holiday -> congratulateAllChats(holiday.getMediaType(), chats, holiday.getMessageText()));
    }

    private void congratulateAllChats(MediaType mediaType, List<jooq.tables.pojos.ChatDb> chats, String messageText) {
        chats.stream().map(ChatDb::getChatId)
                .forEach(chatId -> messageService.sendMediaToChat(chatId, mediaService.getRandomMediaByType(mediaType, chatId), messageText));

    }
}

