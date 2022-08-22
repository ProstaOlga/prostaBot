package com.inventory.prosta.bot.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class Account {
    private Long id;
    private String name;
    private LocalDate birthday;
    private List<Chat> chats;
}
