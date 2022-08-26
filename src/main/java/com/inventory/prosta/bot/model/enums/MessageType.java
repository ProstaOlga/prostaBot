package com.inventory.prosta.bot.model.enums;

import com.inventory.prosta.bot.service.comands.AdminSettingCommand;
import com.inventory.prosta.bot.service.comands.BotChatSettingCommand;
import com.inventory.prosta.bot.service.comands.Command;
import com.inventory.prosta.bot.service.comands.NonCommand;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum MessageType  {
    BOT_CHAT_SETTINGS("/bot", BotChatSettingCommand.class),
    ADMIN_SETTINGS("/admin", AdminSettingCommand.class),
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