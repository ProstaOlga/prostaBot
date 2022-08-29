package com.inventory.prosta.bot.service.impl;

import com.inventory.prosta.bot.telegram.TelegramMediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
@Service
@RequiredArgsConstructor
public class SchedulerServiceImpl {

    private final TelegramMediaService telegramMediaService;


}
