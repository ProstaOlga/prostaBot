package com.inventory.prosta.bot.util;
import com.inventory.prosta.bot.model.enums.MediaFormat;
import com.inventory.prosta.bot.model.enums.MediaType;
import jooq.tables.pojos.Media;
import org.apache.commons.lang3.RandomUtils;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MediaDataSource implements DataSource<Media>{

//    @Resource(name = "virtual_assistant_services_2.jpg")
//    private InputStream testMedia;

    @Override
    public List<Media> getList(Media instance, int size) {
        List<Media> result = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            Media media = new Media();
            media.setId(UUID.randomUUID());
            media.setMedia(instance.getMedia());
            media.setMediaType(instance.getMediaType());
            media.setMediaFormat(instance.getMediaFormat());
            result.add(media);
        }

        return result;
    }

    @Override
    public Media getSingleInstance() {
        Media media = new Media();
        media.setId(UUID.randomUUID());
        media.setMedia(loadMedia());
        media.setMediaType(MediaType.MORNING_GREETING.toString());
        media.setMediaFormat(MediaFormat.JPG.getFormat());

        return media;
    }


    private byte[] loadMedia(){
        String pathName = "C:\\Users\\May\\IdeaProjects\\prostaBot\\src\\test\\resources\\virtual_assistant_services_2.jpg";


        File fi = new File(pathName);
        byte[] fileContent;

        try {
           fileContent = Files.readAllBytes(fi.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return fileContent;
    }
}
