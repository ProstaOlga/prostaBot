package com.inventory.prosta.bot.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.LocalDateTime;
import java.util.function.Consumer;
import jooq.tables.pojos.Account;

@Getter
@Setter
@SuperBuilder
public class BirthdayAnswerEvent extends AnswerEvent{

    private Account account;
    private Consumer<Account> onSaveBirthday;

    @Override
    public void execute() {
        onSaveBirthday.accept(this.account);
    }

}
