package com.inventory.prosta.bot.service.impl;

import com.inventory.prosta.bot.Context.AnswerContext;
import com.inventory.prosta.bot.model.AnswerEvent;
import com.inventory.prosta.bot.model.enums.MediaType;
import com.inventory.prosta.bot.repository.ChatRepo;
import com.inventory.prosta.bot.service.api.AccountService;
import com.inventory.prosta.bot.service.api.HolidayService;
import com.inventory.prosta.bot.service.api.MessageService;
import jooq.tables.pojos.Account;
import jooq.tables.pojos.ChatDb;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class SchedulerServiceImpl {

    private final ChatRepo chatRepo;
    private final MessageService messageService;
    private final HolidayService holidayService;
    private final AccountService accountService;
    private final AnswerContext<? extends AnswerEvent> answerContext;

    @Scheduled(cron = "0 0 8 ? * *")
//    @Scheduled(fixedRate = 80000)
    public void morningNotice() {
        log.info("Morning notice scheduler worked");
        List<ChatDb> chats = chatRepo.getGroupChatsDailyGreetingOn();

        messageService.sendMediaToChats(MediaType.MORNING_GREETING, chats);
    }

    @Scheduled(cron = "0 0 23 ? * *")
//    @Scheduled(fixedRate = 80000)
    public void nightNotice() {
        log.info("Night notice scheduler worked");
        List<ChatDb> chats = chatRepo.getGroupChatsDailyGreetingOn();
        messageService.sendMediaToChats(MediaType.GOOD_NIGHT, chats);
    }

    @Scheduled(cron = "0 0 10 ? * *")
//    @Scheduled(fixedRate = 80000)
    public void birthday() {
        log.info("Birthday scheduler worked");
        List<Account> accountsWithBirthdayNow = accountService.getAccountsWithBirthdayNow();

        if (!accountsWithBirthdayNow.isEmpty()) {
            accountsWithBirthdayNow.forEach(user -> holidayService.congratulateUserWithBirthday(user.getTelegramId()));
        }
    }

    @Scheduled(cron = "0 0 13 ? * *")
//    @Scheduled(fixedRate = 80000)
    public void holiday() {
        log.info("Holiday scheduler worked");
        List<ChatDb> chats = chatRepo.getGroupChatsHolidayOn();

        if (!chats.isEmpty()) {
            holidayService.congratulateWithTodayHolidays(chats);
        }
    }


    @Scheduled(cron = "0 * * ? * *")
    public void cleanAnswerContext() {
        if (!answerContext.getAnswerMap().isEmpty()) {

            List<Long> expiredKeys = answerContext.getAnswerMap().entrySet().stream()
                    .filter(event -> event.getValue().getCreateDate().plusMinutes(5).isBefore(LocalDateTime.now()))
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());

            answerContext.removeAll(expiredKeys);
        }
    }

}
