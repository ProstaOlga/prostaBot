package com.inventory.prosta.bot.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;


@Builder
@Getter
@Setter
public class CheckAnswer {
    private boolean chatCheck;
    private boolean userCheck;
    private boolean logicCheck;

    public boolean getCheckingResult(){
        return chatCheck && userCheck && logicCheck;
    }
    
}
