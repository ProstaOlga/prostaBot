package com.inventory.prosta.bot.model;

import jooq.tables.pojos.Account;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.function.Consumer;

@Getter
@Setter
@SuperBuilder
public class BirthdayAnswerEvent extends AnswerEvent {

    private Account account;
    private Consumer<Account> onSaveBirthday;

    @Override
    public void execute() {
        onSaveBirthday.accept(this.account);
    }

}
