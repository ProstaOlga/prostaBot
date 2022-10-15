package com.inventory.prosta.bot.service.impl;

import com.inventory.prosta.bot.model.enums.MediaType;
import com.inventory.prosta.bot.repository.MediaRepo;
import com.inventory.prosta.bot.service.api.MediaService;
import jooq.tables.pojos.Media;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import jooq.tables.pojos.MediaChat;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MediaServiceImpl implements MediaService {

    private final MediaRepo mediaRepo;
    private final static int PLUS_WEEKS = 3;

    @Override
    public Media getRandomMediaByType(MediaType mediaType) {
        return mediaRepo.getRandomMedia(mediaType);
    }

    @Override
    public Media getRandomMediaByTypeForChat(MediaType mediaType, Long chatId){
        List<UUID> mediaIdList = mediaRepo.getMediaChatFromChat(chatId).stream()
                .map(MediaChat::getMediaId)
                .collect(Collectors.toList());

        return mediaRepo.getRandomMedia(mediaIdList, mediaType);
    }

    @Override
    public void addNewImg(Blob blob, MediaType mediaType) {
    }

    @Override
    public InputFile mediaToInputFile(Media media) {
        var bytes = media.getMedia();
        InputStream in = new ByteArrayInputStream(bytes);

        return new InputFile(in, media.getId().toString());
    }

    @Override
    public void addToMediaChatTable(UUID mediaId, Long chatId, LocalDate nowDate) {
        if (!existInMediaChatTable(mediaId, chatId)){
            MediaChat mediaChat = new MediaChat();
            mediaChat.setMediaId(mediaId);
            mediaChat.setChatId(chatId);
            mediaChat.setExpiredAt(nowDate.plusWeeks(PLUS_WEEKS));

            mediaRepo.addMediaChat(mediaChat);
        }
    }

    @Override
    public boolean existInMediaChatTable(UUID mediaId, Long chatId){
        return mediaRepo.existInMediaChat(mediaId, chatId);
    }

    @Override
    public void removedExpiredDateMediaChat(LocalDate nowDate){
        List<MediaChat> expiredMediaChats = mediaRepo.getExpiredDateMediaChat(nowDate);

        expiredMediaChats.forEach(mediaRepo::removeMediaChat);
    }

}
