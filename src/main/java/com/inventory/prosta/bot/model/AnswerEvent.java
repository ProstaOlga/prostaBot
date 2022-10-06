package com.inventory.prosta.bot.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.LocalDateTime;
@Getter
@Setter
@SuperBuilder
public abstract class AnswerEvent {

    private LocalDateTime createDate;
    private Long chatId;
    private Long userid;


    public abstract void execute();
}
