package com.inventory.prosta.bot.model.answer;

import jooq.tables.pojos.Media;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Consumer;

@Getter
@Setter
@Slf4j
@SuperBuilder
public class MediaAnswerEvent extends AnswerEvent{

    private Media media;
    private Consumer<Media> onSaveMedia;

    @Override
    public void execute() {
        onSaveMedia.accept(this.media);
    }
}
