package com.inventory.prosta.bot.telegram;

import com.inventory.prosta.bot.model.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;

@Service
@RequiredArgsConstructor
public class TelegramMediaService {

    private final TelegramBotContext telegramBotContext;

    public void sendImg(InputFile img, Long chatId){
        SendPhoto sendPhoto = SendPhoto.builder()
                .photo(img)
                .chatId(chatId)
                .build();

        telegramBotContext.execute(sendPhoto);
    }

    public void sendImgAndText(InputFile img, Message message, Long chatId){
        SendPhoto sendPhoto = SendPhoto.builder()
                .photo(img)
                .chatId(chatId)
                .caption(message.getText())
                .build();

        telegramBotContext.execute(sendPhoto);
    }
}
