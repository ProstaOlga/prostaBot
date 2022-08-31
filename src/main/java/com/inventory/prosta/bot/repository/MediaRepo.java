package com.inventory.prosta.bot.repository;

import com.inventory.prosta.bot.model.enums.MediaType;
import jooq.tables.daos.MediaDao;
import jooq.tables.pojos.Media;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

import java.util.List;

import static jooq.tables.Media.MEDIA;
import static org.jooq.impl.DSL.rand;

@Repository
@RequiredArgsConstructor
public class MediaRepo {

    private final DSLContext dsl;
    private MediaDao mediaDao;

    @PostConstruct
    void initMediaDao(){
        mediaDao = new MediaDao(dsl.configuration());
    }

    public void save(Media media){
        mediaDao.insert();
    }

    public Media getRandomImg(MediaType mediaType){
        return dsl.select()
                .from(MEDIA)
                .where(MEDIA.MEDIA_TYPE.eq(mediaType.toString()))
                .orderBy(rand())
                .limit(1)
                .fetchOneInto(Media.class);
    }

}
