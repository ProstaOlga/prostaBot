package com.inventory.prosta.bot.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.jooq.impl.QOM;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
public abstract class AnswerEvent {

    private LocalDateTime createDate;
    private Long chatId;
    private Long userid;
    private Integer messageId;

    public abstract void execute();
}
