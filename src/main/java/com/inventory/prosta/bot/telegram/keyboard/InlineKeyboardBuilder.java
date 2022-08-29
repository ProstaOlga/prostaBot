package com.inventory.prosta.bot.telegram.keyboard;

import com.inventory.prosta.bot.model.enums.ButtonEnum;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class InlineKeyboardBuilder {
    public InlineKeyboardMarkup getStartPageKeyboard(){
        List<ButtonEnum> row1 = List.of(ButtonEnum.INFO, ButtonEnum.NOTIFICATIONS);

        return  InlineKeyboardMarkup.builder()
                .keyboardRow(getKeyboardRow(row1))
                .build();
    }

    private List<InlineKeyboardButton> getKeyboardRow(List<ButtonEnum> buttonEnums){
        return buttonEnums.stream()
                .map(ButtonEnum::renderButton)
                .collect(Collectors.toList());
    }

}
