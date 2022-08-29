package com.inventory.prosta.bot.service.api;

import com.inventory.prosta.bot.model.enums.MediaType;
import org.telegram.telegrambots.meta.api.objects.InputFile;

import java.sql.Blob;

public interface MediaService {
    /**
     * Получить рандомное изображение по типу изображения
     */
    InputFile getRandomImgByType(MediaType mediaType);

    /**
     * Добавить новое изображение
     */
    void addNewImg(Blob blob, MediaType mediaType);
}
