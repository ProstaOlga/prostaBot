package com.inventory.prosta.bot.model.enums;

import com.inventory.prosta.bot.service.comands.SettingsCommand;
import com.inventory.prosta.bot.service.comands.adminCommands.AdminSettingCommand;
import com.inventory.prosta.bot.service.comands.MainPageCommand;
import com.inventory.prosta.bot.service.comands.Command;
import com.inventory.prosta.bot.service.comands.NonCommand;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum MessageType  {
    BOT_CHAT_SETTINGS("/bot", MainPageCommand.class),
    ADMIN_SETTINGS("/admin", AdminSettingCommand.class),
    SETTINGS("/settings", SettingsCommand.class),
    NON_COMMAND("", NonCommand.class);

    private final String text;
    private final Class<? extends Command> clazz;

    public static Class<? extends Command> getCommandClass(String command){
        return Arrays.stream(MessageType.values())
                .filter(message -> message.getText().equals(command))
                .findFirst()
                .orElse(NON_COMMAND)
                .getClazz();
    }
}
