package com.inventory.prosta.bot.model.enums;

import lombok.Getter;
import lombok.SneakyThrows;

import java.util.Arrays;

@Getter
public enum MediaFormat {
    GIF(".gif", "video"),
    JPG(".jpg", "image"),
    PNG(".png", "image");

    private String format;
    private String tgType;

    MediaFormat(String format, String tgType) {
        this.format = format;
        this.tgType = tgType;
    }

    @SneakyThrows
    public static MediaFormat getFormatFromUrl(String url){
        return Arrays.stream(MediaFormat.values())
                .filter(format -> url.contains(format.getFormat()))
                .findFirst()
                .orElseThrow(() -> new Exception(String.format("Media file format not recognized in url %s.", url)));
    }

    public static boolean isImage(String format){
        return Arrays.stream(MediaFormat.values())
                .filter(f -> f.tgType.equals("image"))
                .anyMatch(f -> format.equals(f.getFormat()));
    }

    public static boolean isVideo(String format){
        return Arrays.stream(MediaFormat.values())
                .filter(f -> f.tgType.equals("video"))
                .anyMatch(f -> format.equals(f.getFormat()));
    }
}


