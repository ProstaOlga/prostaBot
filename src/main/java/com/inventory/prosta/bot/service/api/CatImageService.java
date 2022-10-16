package com.inventory.prosta.bot.service.api;
import jooq.tables.pojos.Media;

public interface CatImageService {
    /**
     * Вернуть Media, содержащее blob со случайным изображением котика.
     * @return Media.class - класс, хранящий в себе blob с изображением и метоинформацией об этом изорбражении.
     */
    Media getRandomCatMedia();


}
