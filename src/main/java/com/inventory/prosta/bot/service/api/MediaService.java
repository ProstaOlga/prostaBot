package com.inventory.prosta.bot.service.api;

import com.inventory.prosta.bot.model.enums.MediaType;
import org.springframework.cache.annotation.Cacheable;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import jooq.tables.pojos.Media;

import java.sql.Blob;

public interface MediaService {
    /**
     * Получить рандомное изображение по типу изображения
     */
    Media getRandomImgByType(MediaType mediaType);

    /**
     * Добавить новое изображение
     */
    void addNewImg(Blob blob, MediaType mediaType);

    InputFile mediaToInputFile(Media media);
}
