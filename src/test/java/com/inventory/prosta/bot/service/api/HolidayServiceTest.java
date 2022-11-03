package com.inventory.prosta.bot.service.api;

import com.inventory.prosta.bot.BaseUnitTest;
import com.inventory.prosta.bot.model.enums.Holiday;
import com.inventory.prosta.bot.model.enums.MediaType;
import com.inventory.prosta.bot.repository.ChatRepo;
import com.inventory.prosta.bot.service.impl.HolidayServiceImpl;
import com.inventory.prosta.bot.util.AccountDataSource;
import com.inventory.prosta.bot.util.ChatDataSource;
import com.inventory.prosta.bot.util.MediaDataSource;
import jooq.tables.pojos.Account;
import jooq.tables.pojos.ChatDb;
import jooq.tables.pojos.Media;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.MonthDay;
import java.util.List;

class HolidayServiceTest extends BaseUnitTest {

    @Mock
    private ChatService chatService;

    @Mock
    private MessageService messageService;

    @Mock
    private MediaService mediaService;

    @Mock
    private ChatRepo chatRepo;




    @Test
    void congratulateUserWithBirthdayBirthdayNoticeTrue() {
        HolidayService holidayService = new HolidayServiceImpl(chatService, messageService, mediaService, chatRepo);
        var accountDataSource = new AccountDataSource();
        var chatDataSource = new ChatDataSource();
        var mediaDataSource = new MediaDataSource();

        Account account = accountDataSource.getSingleInstance();
        ChatDb chat = chatDataSource.getSingleInstance();
        chat.setBirthdayNotice(true);
        List<ChatDb> chats = chatDataSource.getList(chat, 5);
        Media media = mediaDataSource.getSingleInstance();
        media.setMediaType(MediaType.BIRTHDAY.toString());

        Mockito.when(chatService.getAccountChats(account.getTelegramId()))
                .thenReturn(chats);
        Mockito.when(mediaService.getRandomMediaByType(ArgumentMatchers.any(MediaType.class), ArgumentMatchers.anyLong()))
                        .thenReturn(media);

        holidayService.congratulateUserWithBirthday(account);

        Mockito.verify(chatService, Mockito.times(1)).getAccountChats(ArgumentMatchers.anyLong());
        Mockito.verify(chatService).getAccountChats(ArgumentMatchers.argThat(
                (Long accountId) -> accountId.equals(account.getTelegramId())
        ));
        Mockito.verify(messageService, Mockito.times(5)).sendMediaToChat(ArgumentMatchers.anyLong(), ArgumentMatchers.any(Media.class));
        Mockito.verify(messageService, Mockito.times(5)).sendMessageToChat(ArgumentMatchers.anyLong(), ArgumentMatchers.anyString());
        Mockito.verify(mediaService, Mockito.times(5)).getRandomMediaByType(ArgumentMatchers.any(MediaType.class), ArgumentMatchers.anyLong());
    }

    @Test
    void congratulateUserWithBirthdayBirthdayNoticeFalse() {
        HolidayService holidayService = new HolidayServiceImpl(chatService, messageService, mediaService, chatRepo);
        var accountDataSource = new AccountDataSource();
        var chatDataSource = new ChatDataSource();
        var mediaDataSource = new MediaDataSource();

        Account account = accountDataSource.getSingleInstance();
        ChatDb chat = chatDataSource.getSingleInstance();
        chat.setBirthdayNotice(false);
        List<ChatDb> chats = chatDataSource.getList(chat, 5);
        Media media = mediaDataSource.getSingleInstance();
        media.setMediaType(MediaType.BIRTHDAY.toString());

        Mockito.when(chatService.getAccountChats(account.getTelegramId()))
                .thenReturn(chats);

        holidayService.congratulateUserWithBirthday(account);

        Mockito.verify(chatService, Mockito.times(1)).getAccountChats(ArgumentMatchers.anyLong());
        Mockito.verify(chatService).getAccountChats(ArgumentMatchers.argThat(
                (Long accountId) -> accountId.equals(account.getTelegramId())
        ));
        Mockito.verify(messageService, Mockito.times(0)).sendMediaToChat(ArgumentMatchers.anyLong(), ArgumentMatchers.any(Media.class));
        Mockito.verify(messageService, Mockito.times(0)).sendMessageToChat(ArgumentMatchers.anyLong(), ArgumentMatchers.anyString());
        Mockito.verify(mediaService, Mockito.times(0)).getRandomMediaByType(ArgumentMatchers.any(MediaType.class), ArgumentMatchers.anyLong());
    }

    @Test
    void birthdayReminderBirthdayNoticeTrueGroupChats() {
        HolidayService holidayService = new HolidayServiceImpl(chatService, messageService, mediaService, chatRepo);
        var accountDataSource = new AccountDataSource();
        var chatDataSource = new ChatDataSource();
        var mediaDataSource = new MediaDataSource();

        Account account = accountDataSource.getSingleInstance();
        ChatDb chat = chatDataSource.getSingleInstance();
        chat.setBirthdayNotice(true);
        List<ChatDb> chats = chatDataSource.getList(chat, 5);
        Media media = mediaDataSource.getSingleInstance();
        media.setMediaType(MediaType.BIRTHDAY.toString());

        Mockito.when(chatService.getAccountChats(account.getTelegramId()))
                .thenReturn(chats);

        holidayService.birthdayReminder(account);

        Mockito.verify(chatService, Mockito.times(1)).getAccountChats(ArgumentMatchers.anyLong());
        Mockito.verify(chatService).getAccountChats(ArgumentMatchers.argThat(
                (Long accountId) -> accountId.equals(account.getTelegramId())
        ));
        Mockito.verify(messageService, Mockito.times(5)).sendMessageToChat(ArgumentMatchers.anyLong(), ArgumentMatchers.anyString());
    }

    @Test
    void birthdayReminderBirthdayNoticeTruePrivateChats() {
        HolidayService holidayService = new HolidayServiceImpl(chatService, messageService, mediaService, chatRepo);
        var accountDataSource = new AccountDataSource();
        var chatDataSource = new ChatDataSource();
        var mediaDataSource = new MediaDataSource();

        Account account = accountDataSource.getSingleInstance();
        ChatDb chat = chatDataSource.getSingleInstance();
        chat.setBirthdayNotice(true);
        chat.setChatType("private");
        List<ChatDb> chats = chatDataSource.getList(chat, 5);
        Media media = mediaDataSource.getSingleInstance();
        media.setMediaType(MediaType.BIRTHDAY.toString());

        Mockito.when(chatService.getAccountChats(account.getTelegramId()))
                .thenReturn(chats);

        holidayService.birthdayReminder(account);

        Mockito.verify(chatService, Mockito.times(1)).getAccountChats(ArgumentMatchers.anyLong());
        Mockito.verify(chatService).getAccountChats(ArgumentMatchers.argThat(
                (Long accountId) -> accountId.equals(account.getTelegramId())
        ));
        Mockito.verify(messageService, Mockito.times(0)).sendMessageToChat(ArgumentMatchers.anyLong(), ArgumentMatchers.anyString());
    }

    @Test
    void birthdayReminderBirthdayNoticeFalseGroupChats() {
        HolidayService holidayService = new HolidayServiceImpl(chatService, messageService, mediaService, chatRepo);
        var accountDataSource = new AccountDataSource();
        var chatDataSource = new ChatDataSource();
        var mediaDataSource = new MediaDataSource();

        Account account = accountDataSource.getSingleInstance();
        ChatDb chat = chatDataSource.getSingleInstance();
        chat.setBirthdayNotice(false);
        List<ChatDb> chats = chatDataSource.getList(chat, 5);
        Media media = mediaDataSource.getSingleInstance();
        media.setMediaType(MediaType.BIRTHDAY.toString());

        Mockito.when(chatService.getAccountChats(account.getTelegramId()))
                .thenReturn(chats);

        holidayService.birthdayReminder(account);

        Mockito.verify(chatService, Mockito.times(1)).getAccountChats(ArgumentMatchers.anyLong());
        Mockito.verify(chatService).getAccountChats(ArgumentMatchers.argThat(
                (Long accountId) -> accountId.equals(account.getTelegramId())
        ));
        Mockito.verify(messageService, Mockito.times(0)).sendMessageToChat(ArgumentMatchers.anyLong(), ArgumentMatchers.anyString());
    }

    @Test
    void congratulateWithTodayHolidays() {
        HolidayService holidayService = new HolidayServiceImpl(chatService, messageService, mediaService, chatRepo);
        var chatDataSource = new ChatDataSource();
        var mediaDataSource = new MediaDataSource();

        ChatDb chat = chatDataSource.getSingleInstance();
        chat.setHolidayNotice(true);
        List<ChatDb> chats = chatDataSource.getList(chat, 5);
        Media media = mediaDataSource.getSingleInstance();
        media.setMediaType(MediaType.CHRISTMAS.toString());

        Mockito.when(mediaService.getRandomMediaByType(ArgumentMatchers.any(MediaType.class), ArgumentMatchers.anyLong()))
                .thenReturn(media);
        Mockito.when(chatRepo.getChatsHolidayOn())
                .thenReturn(chats);

        holidayService.congratulateWithTodayHolidays(Holiday.CHRISTMAS);

        Mockito.verify(chatRepo).getChatsHolidayOn();
        Mockito.verify(mediaService, Mockito.times(5)).getRandomMediaByType(ArgumentMatchers.any(MediaType.class), ArgumentMatchers.anyLong());
        Mockito.verify(messageService, Mockito.times(5)).sendMediaToChat(
                ArgumentMatchers.anyLong(), ArgumentMatchers.any(Media.class), ArgumentMatchers.anyString()
        );
    }

    @Test
    void congratulateWithTodayHolidaysNoticeFalse() {
        HolidayService holidayService = new HolidayServiceImpl(chatService, messageService, mediaService, chatRepo);

        Mockito.when(chatRepo.getChatsHolidayOn())
                .thenReturn(List.of());

        holidayService.congratulateWithTodayHolidays(Holiday.CHRISTMAS);

        Mockito.verify(chatRepo).getChatsHolidayOn();
        Mockito.verify(mediaService, Mockito.times(0)).getRandomMediaByType(ArgumentMatchers.any(MediaType.class), ArgumentMatchers.anyLong());
        Mockito.verify(messageService, Mockito.times(0)).sendMediaToChat(
                ArgumentMatchers.anyLong(), ArgumentMatchers.any(Media.class), ArgumentMatchers.anyString()
        );
    }
}