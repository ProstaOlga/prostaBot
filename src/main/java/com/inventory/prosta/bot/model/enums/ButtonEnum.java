package com.inventory.prosta.bot.model.enums;

import com.inventory.prosta.bot.util.ResourceBundleUtil;
import com.inventory.prosta.bot.util.Symbols;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum ButtonEnum {
    TO_MAIN("toMain", Symbols.BACK_ARROW + ResourceBundleUtil.getMessageText("button.main")),
    BACK_SETTINGS( "settings", Symbols.BACK_ARROW + ResourceBundleUtil.getMessageText("button.back")),
    CANCEL_BIRTH_DATE_ENTER("cancelBirthDateEnter", ResourceBundleUtil.getMessageText("button.cancel")),
    SETTINGS("settings", ResourceBundleUtil.getMessageText("button.settings")),
    DATE_OF_BIRTH_SETTINGS("dAteOfBirth", ResourceBundleUtil.getMessageText("button.date.of.birth.settings")),
    ENTER_USER_BIRTH_DATE( "setUserBirth", ""),
    NOTIFICATION_SETTINGS("notificationSettings", ResourceBundleUtil.getMessageText("button.notification.settings")),
    HOLIDAY_CONGRATULATION_ENABLE("holidaySwitchON", Symbols.RED_CIRCLE_EMOJI + ResourceBundleUtil.getMessageText("button.holiday.congratulation")),
    HOLIDAY_CONGRATULATION_DISABLE("holidaySwitchOFF", Symbols.GREEN_CIRCLE_EMOJI + ResourceBundleUtil.getMessageText("button.holiday.congratulation")),
    BIRTHDAY_CONGRATULATION_ENABLE("birthdaySwitchON", Symbols.RED_CIRCLE_EMOJI + ResourceBundleUtil.getMessageText("button.birthday.congratulation")),
    BIRTHDAY_CONGRATULATION_DISABLE("birthdaySwitchOFF", Symbols.GREEN_CIRCLE_EMOJI + ResourceBundleUtil.getMessageText("button.birthday.congratulation")),
    DAILY_GREETING_ENABLE("dailyGreetingSwitchON", Symbols.RED_CIRCLE_EMOJI + ResourceBundleUtil.getMessageText("button.daily.greeting")),
    DAILY_GREETING_DISABLE("dailyGreetingSwitchOFF", Symbols.GREEN_CIRCLE_EMOJI + ResourceBundleUtil.getMessageText("button.daily.greeting")),
    CAT("getCat", ResourceBundleUtil.getMessageText("button.cat")),
    CAT_ANOTHER("getCat", ResourceBundleUtil.getMessageText("button.cat.another")),
    INFO( "info", ResourceBundleUtil.getMessageText("button.info")),
    BACK_INFO( "info", Symbols.BACK_ARROW + ResourceBundleUtil.getMessageText("button.back")),
    INFO_BOT_FUNCTION( "infoWhatCanDo", ResourceBundleUtil.getMessageText("button.info.function")),
    INFO_SETTINGS("infoSettings", ResourceBundleUtil.getMessageText("button.info.settings")),
    ADMIN_MEDIA_SELECT_TYPE("selectMediaType", ResourceBundleUtil.getMessageText("button.admin.media.add")),
    ADMIN_MEDIA_CREATE_ANSWER_EVENT("createMediaAnswer", ResourceBundleUtil.getMessageText("button.admin.media.download")),
    ADMIN_MEDIA_CANCEL_ANSWER_EVENT("cancelMediaAnswer", ResourceBundleUtil.getMessageText("button.cancel")),
    TO_ADMIN("admin", Symbols.BACK_ARROW + ResourceBundleUtil.getMessageText("button.back"));

    private final String command;
    private final String text;


    public InlineKeyboardButton renderButton() {
        return InlineKeyboardButton.builder()
                .text(this.text)
                .callbackData(this.command)
                .build();
    }

    public static ButtonEnum getButtonEnumFromString(String string){
        return Arrays.stream(ButtonEnum.values())
                .filter(button -> button.getCommand().equals(string))
                .findFirst()
                .orElseThrow();
    }

}
