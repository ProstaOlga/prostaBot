package com.inventory.prosta.bot.model.enums;

public enum MediaFormat {
    GIF(".gif"),
    JPG(".jpg");

    private String format;

    private MediaFormat(String format) {
        this.format = format;
    }
}
