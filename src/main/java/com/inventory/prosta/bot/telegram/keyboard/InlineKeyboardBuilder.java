package com.inventory.prosta.bot.telegram.keyboard;

import com.inventory.prosta.bot.model.UpdateContext;
import com.inventory.prosta.bot.model.enums.ButtonEnum;
import com.inventory.prosta.bot.service.api.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class InlineKeyboardBuilder {

    private final ChatService chatService;
    private final UpdateContext updateContext;

    public InlineKeyboardMarkup getStartPageKeyboard() {
        List<ButtonEnum> row1 = List.of(ButtonEnum.INFO, ButtonEnum.SETTINGS);
        List<ButtonEnum> row2 = List.of(ButtonEnum.CAT);

        return InlineKeyboardMarkup.builder()
                .keyboardRow(getKeyboardRow(row1))
                .keyboardRow(getKeyboardRow(row2))
                .build();
    }

    public InlineKeyboardMarkup getSettingsKeyboard() {
        Long chatId = updateContext.getChatId();
        List<ButtonEnum> row1 = List.of(getBirthdaySwitchButton(chatId));
        List<ButtonEnum> row2 = List.of(getHolidaySwitchButton(chatId));
        List<ButtonEnum> row3 = List.of(getDailySwitchButton(chatId));
        List<ButtonEnum> row4 = List.of(ButtonEnum.TO_MAIN);

        return InlineKeyboardMarkup.builder()
                .keyboardRow(getKeyboardRow(row1))
                .keyboardRow(getKeyboardRow(row2))
                .keyboardRow(getKeyboardRow(row3))
                .keyboardRow(getKeyboardRow(row4))
                .build();
    }


    public InlineKeyboardMarkup getCatKeyboard() {
        List<ButtonEnum> row1 = List.of(ButtonEnum.CAT_ANOTHER, ButtonEnum.TO_MAIN);

        return InlineKeyboardMarkup.builder()
                .keyboardRow(getKeyboardRow(row1))
                .build();
    }

    private ButtonEnum getBirthdaySwitchButton(Long chatId) {
        return chatService.isBirthdayNoticeOn(chatId)
                ? ButtonEnum.BIRTHDAY_CONGRATULATION_DISABLE
                : ButtonEnum.BIRTHDAY_CONGRATULATION_ENABLE;
    }

    private ButtonEnum getHolidaySwitchButton(Long chatId) {
        return chatService.isHolidayNoticeOn(chatId)
                ? ButtonEnum.HOLIDAY_CONGRATULATION_DISABLE
                : ButtonEnum.HOLIDAY_CONGRATULATION_ENABLE;
    }

    private ButtonEnum getDailySwitchButton(Long chatId) {
        return chatService.isDailyNoticeOn(chatId)
                ? ButtonEnum.DAILY_GREETING_DISABLE
                : ButtonEnum.DAILY_GREETING_ENABLE;
    }

    private List<InlineKeyboardButton> getKeyboardRow(List<ButtonEnum> buttonEnums) {
        return buttonEnums.stream()
                .map(ButtonEnum::renderButton)
                .collect(Collectors.toList());
    }

}
