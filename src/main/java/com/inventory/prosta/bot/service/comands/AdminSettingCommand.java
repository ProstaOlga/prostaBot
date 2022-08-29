package com.inventory.prosta.bot.service.comands;

import com.inventory.prosta.bot.model.Message;
import com.inventory.prosta.bot.model.enums.MediaType;
import com.inventory.prosta.bot.service.api.MediaService;
import com.inventory.prosta.bot.telegram.TelegramMediaService;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class AdminSettingCommand implements Command{
    private final TelegramMediaService telegramMediaService;
    private final MediaService mediaService;

    void testMessage(Long chatId){
        InputFile file = mediaService.getRandomImgByType(MediaType.CAT_DAY);
        telegramMediaService.sendImg(file, chatId);
    }
    @Override
    public BotApiMethod<?> execute(Update update) {
       testMessage(update.getMessage().getChatId());
       return null;
    }
}
