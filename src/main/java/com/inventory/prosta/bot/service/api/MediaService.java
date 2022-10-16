package com.inventory.prosta.bot.service.api;

import com.inventory.prosta.bot.model.enums.MediaType;
import jooq.tables.pojos.Media;
import org.telegram.telegrambots.meta.api.objects.InputFile;

import java.time.LocalDate;
import java.util.UUID;

public interface MediaService {
    /**
     * Возвращает случаное Media того же mediaType, который передани в аргументе.
     *
     * @param mediaType - привязывет изображение к событию,
     *                  на которое данное изображение будет отправляться в чаты.
     * @return Media.class - класс, хранящий в себе blob с изображением и метоинформацией об этом изорбражении.
     */
    Media getRandomMediaByType(MediaType mediaType);

    /**
     * Возвращает случаное Media того же mediaType, который передани в аргументе,
     * и которое еще не использвалось в чате с chatId.
     *
     * @param mediaType - привязывет изображение к событию,
     *                  на которое данное изображение будет отправляться в чаты.
     * @param chatId    - id telegram-чата, в который планируется отправлять Media
     * @return Media.class - класс, хранящий в себе blob с изображением и метоинформацией об этом изорбражении.
     */
    Media getRandomMediaByType(MediaType mediaType, Long chatId);

    /**
     * Преобразует Media.class в InputFile.
     *
     * @param media - класс, хранящий в себе blob с изображением и метоинформацией об этом изорбражении.
     * @return InputFile.class - input файл, используемый TelegramBotAPI для загрузки файла на сервер Telegram.
     */
    InputFile mediaToInputFile(Media media);

    /**
     * Создает в базе данных новую запись привязки Media к телеграмм-чату.
     * Позволяет избежать повторного отправления одного и  того-же изображения в определенный чат.
     *
     * @param mediaUuid - UUID необходимого media.
     * @param chatId    - id telegram - чата, в который было отправлено media.
     * @param expiredAt - дата удаления записи MediaChat из базы данных.
     */
    void addToMediaChatTable(UUID mediaUuid, Long chatId, LocalDate expiredAt);

    /**
     * Проверяет отправлялся ли media с данным UUID  в чат с chatId в ближайшее время.
     *
     * @param mediaUuid - id media, который необхзодимо проверить.
     * @param chatId    - id чата в который планируется отправлять media c переданным mediaUuid.
     * @return возвращает true, если в ближайшее время media с данным UUID отправлялось в чат с переданным chatId
     */
    boolean existInMediaChatTable(UUID mediaUuid, Long chatId);

    /**
     * Удаляет MediaChat с истекшей датой expiredAt.
     *
     * @param localDate - текущая дата.
     */
    void removedExpiredDateMediaChat(LocalDate localDate);
}
