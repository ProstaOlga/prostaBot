package com.inventory.prosta.bot.model.answer;

import com.inventory.prosta.bot.model.answer.AnswerEvent;
import jooq.tables.pojos.Account;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Consumer;

@Getter
@Setter
@Slf4j
@SuperBuilder
public class BirthdayAnswerEvent extends AnswerEvent {

    private Account account;
    private Consumer<Account> onSaveBirthday;

    @Override
    public void execute() {
        onSaveBirthday.accept(this.account);
    }

}
