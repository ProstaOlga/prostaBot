package com.inventory.prosta.bot.service.impl;

import com.inventory.prosta.bot.Context.AnswerContext;
import com.inventory.prosta.bot.model.answer.AnswerEvent;
import com.inventory.prosta.bot.model.enums.Holiday;
import com.inventory.prosta.bot.repository.ChatRepo;
import com.inventory.prosta.bot.service.api.*;
import jooq.tables.pojos.Account;
import jooq.tables.pojos.ChatDb;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.MonthDay;
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
    private final MediaService mediaService;
    private final AnswerContext<? extends AnswerEvent> answerContext;
    private final DailyGreetingService dailyGreetingService;

    @Scheduled(cron = "0 0 8 ? * *")
//    @Scheduled(fixedRate = 60000)
    public void morningNotice() {
        log.info("Morning notice scheduler worked");
        dailyGreetingService.sendGoodMorning();
    }

    @Scheduled(cron = "0 0 23 ? * *")
//    @Scheduled(fixedRate = 80000)
    public void nightNotice() {
        log.info("Night notice scheduler worked");
        dailyGreetingService.sendGoodNight();

    }

    @Scheduled(cron = "0 0 10 ? * *")
//    @Scheduled(fixedRate = 80000)
    public void birthday() {
        log.info("Birthday scheduler worked");
        List<Account> accountsWithBirthdayNow = accountService.getAccountsWithBirthdayNow(LocalDate.now());

        if (!accountsWithBirthdayNow.isEmpty()) {
            accountsWithBirthdayNow.forEach(holidayService::congratulateUserWithBirthday);
        }
    }

    @Scheduled(cron = "0 0 10 ? * *")
//    @Scheduled(fixedRate = 60000)
    public void birthdayReminder() {
        log.info("Birthday scheduler worked");
        List<Account> accountsWithBirthdayNow = accountService.getAccountsWithBirthdayNow(LocalDate.now().plusDays(7));

        if (!accountsWithBirthdayNow.isEmpty()) {
            accountsWithBirthdayNow.forEach(holidayService::birthdayReminder);
        }
    }

    @Scheduled(cron = "0 0 13 ? * *")
//    @Scheduled(fixedRate = 80000)
    public void holiday() {
        log.info("Holiday scheduler worked");

        List<Holiday> todayHolidays = Holiday.getHolidays(MonthDay.now());

        if (!todayHolidays.isEmpty()) {
            todayHolidays.forEach(holiday -> holidayService.congratulateWithTodayHolidays(holiday));
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

//        @Scheduled(fixedRate = 80000)
    @Scheduled(cron = "0 0 2 ? * *")
    public void cleanExpiredDateMediaChat() {
        mediaService.removedExpiredDateMediaChat(LocalDate.now());
        }

}
