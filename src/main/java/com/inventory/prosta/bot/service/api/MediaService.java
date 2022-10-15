package com.inventory.prosta.bot.service.api;

import com.inventory.prosta.bot.model.enums.MediaType;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import jooq.tables.pojos.Media;

import java.sql.Blob;
import java.time.LocalDate;
import java.util.UUID;

public interface MediaService {
    /**
     * Получить рандомное изображение по типу изображения
     */
    Media getRandomMediaByType(MediaType mediaType);

    Media getRandomMediaByTypeForChat(MediaType mediaType, Long chatId);

    /**
     * Добавить новое изображение
     */
    void addNewImg(Blob blob, MediaType mediaType);

    InputFile mediaToInputFile(Media media);

    void addToMediaChatTable(UUID mediaUuid, Long chatId, LocalDate expiredAt);

    boolean existInMediaChatTable(UUID mediaUuid, Long chatId);

    void removedExpiredDateMediaChat(LocalDate localDate);
}
