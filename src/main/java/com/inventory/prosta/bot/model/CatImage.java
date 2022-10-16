package com.inventory.prosta.bot.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CatImage {
    private String id;
    private String url;
    private Long width;
    private Long height;
}
