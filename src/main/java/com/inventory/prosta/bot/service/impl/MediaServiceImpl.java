package com.inventory.prosta.bot.service.impl;

import com.inventory.prosta.bot.model.enums.MediaType;
import com.inventory.prosta.bot.model.enums.MessageType;
import com.inventory.prosta.bot.repository.MediaRepo;
import com.inventory.prosta.bot.service.api.MediaService;
import jooq.tables.daos.MediaDao;
import jooq.tables.pojos.Media;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.InputFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Blob;

@Service
@RequiredArgsConstructor
public class MediaServiceImpl implements MediaService {

    private final MediaRepo mediaRepo;

    @Override
    public InputFile getRandomImgByType(MediaType mediaType) {
       Media media =  mediaRepo.getRandomImg(mediaType);

       return mediaToInputFile(media);
    }

    @Override
    public void addNewImg(Blob blob, MediaType mediaType) {
    }

    private InputFile mediaToInputFile(Media media){
        var bytes = media.getMedia();
        InputStream in = new ByteArrayInputStream(bytes);

        return new InputFile(in, media.getId().toString());
    }
}
