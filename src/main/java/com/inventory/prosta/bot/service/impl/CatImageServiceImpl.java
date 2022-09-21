package com.inventory.prosta.bot.service.impl;

import com.inventory.prosta.bot.model.CatImage;
import com.inventory.prosta.bot.model.enums.MediaFormat;
import com.inventory.prosta.bot.model.enums.MediaType;
import com.inventory.prosta.bot.service.api.CatImageService;
import com.inventory.prosta.bot.service.api.MediaService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import jooq.tables.pojos.Media;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CatImageServiceImpl implements CatImageService {
    private final RestTemplate restTemplate;
    private final MediaService mediaService;

    @Value("${catImage.url}")
    private String catURL;

    @SneakyThrows
    @Override
    public Media getRandomCatMedia() {
        var catImages = restTemplate.getForObject(catURL, CatImage[].class);

        List<CatImage> catImageList = List.of(catImages);

        return catImages.length > 0
                ? getMediaFromURL(getCatImage(catImageList))
                : mediaService.getRandomImgByType(MediaType.CAT_DAY);
    }


    private Media getMediaFromURL(CatImage catImage){
        Media media = new Media();

        try {
            var inputStream = new URL(catImage.getUrl()).openStream();
            media.setMedia(inputStream.readAllBytes());
            media.setId(UUID.randomUUID());
            media.setMediaFormat(getMediaFormatFromURL(catImage.getUrl()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return media;
    }

    private Media getMediaFromURL(String url){
        Media media = new Media();

        try {
            var inputStream = new URL(url).openStream();
            media.setMedia(inputStream.readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return media;
    }

    @SneakyThrows
    private String getUrl(List<CatImage> catImageList) {
        return catImageList.stream()
                .map(CatImage::getUrl)
                .findFirst()
                .orElseThrow(() -> new Exception("cat image not found"));

    }

    @SneakyThrows
    private String getImageId(List<CatImage> catImageList){
        return catImageList.stream()
                .map(CatImage::getId)
                .findFirst()
                .orElseThrow(() -> new Exception("cat image not found"));
    }

    @SneakyThrows
    private CatImage getCatImage(List<CatImage> catImageList){
        return catImageList.stream()
                .findFirst()
                .orElseThrow(() -> new Exception("cat image not found"));
    }

    private String getMediaFormatFromURL(String url){
        return MediaFormat.getFormatFromUrl(url).getFormat();
    }
}
