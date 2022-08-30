package com.inventory.prosta.bot.service.impl;

import com.inventory.prosta.bot.model.enums.MediaType;
import com.inventory.prosta.bot.repository.ChatRepo;
import com.inventory.prosta.bot.service.api.MessageService;
import jooq.tables.pojos.ChatDb;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SchedulerServiceImpl {

    private final ChatRepo chatRepo;
    private final MessageService messageService;

    //    @Scheduled(cron = "0 0 8 ? * *")
    @Scheduled(fixedRate = 60000)
    @Transactional
    public void morningNotice() {
        List<ChatDb> chats = chatRepo.getGroupChatsDailyGreetingOn();

        messageService.sendMessageToChats(MediaType.MORNING_GREETING, chats);
    }

    //    @Scheduled(cron = "0 0 8 ? * *")
    @Scheduled(fixedRate = 80000)
    @Transactional
    public void nightNotice() {
        List<ChatDb> chats = chatRepo.getGroupChatsDailyGreetingOn();

        messageService.sendMessageToChats(MediaType.GOOD_NIGHT, chats);
    }


}
