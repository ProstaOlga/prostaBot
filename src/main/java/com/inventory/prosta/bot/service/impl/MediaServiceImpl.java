package com.inventory.prosta.bot.service.impl;

import com.inventory.prosta.bot.model.enums.MediaType;
import com.inventory.prosta.bot.repository.MediaRepo;
import com.inventory.prosta.bot.service.api.MediaService;
import jooq.tables.pojos.Media;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.InputFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MediaServiceImpl implements MediaService {

    private final MediaRepo mediaRepo;

    @Override
    public Media getRandomImgByType(MediaType mediaType) {
        return mediaRepo.getRandomImg(mediaType);
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
    public void addToMediaChatTable(UUID mediaUuid, Long chatId, LocalDate expiredAt) {

    }

    @Override
    public boolean existInMediaChatTable(UUID mediaUuid, Long chatId){
        return false;
    }

    @Override
    public void removedExpiredDateMediaChat(LocalDate localDate){

    }
}
