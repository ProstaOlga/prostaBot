package com.inventory.prosta.bot.service.comands;

import com.inventory.prosta.bot.model.UpdateContext;
import com.inventory.prosta.bot.service.api.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

@Component
@RequiredArgsConstructor
public class AdminSettingCommand implements Command {

    private final MediaService mediaService;
    private final UpdateContext updateContext;


    @Override
    public BotApiMethod<?> execute() {
        return null;
    }
}
