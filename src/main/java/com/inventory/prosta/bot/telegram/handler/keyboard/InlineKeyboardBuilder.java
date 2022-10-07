package com.inventory.prosta.bot.telegram.handler.keyboard;

import com.inventory.prosta.bot.model.UpdateContext;
import com.inventory.prosta.bot.model.enums.ButtonEnum;
import com.inventory.prosta.bot.model.enums.CallbackQueryType;
import com.inventory.prosta.bot.service.api.AccountService;
import com.inventory.prosta.bot.service.api.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import jooq.tables.pojos.Account;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class InlineKeyboardBuilder {

    private final ChatService chatService;
    private final UpdateContext updateContext;
    private final AccountService accountService;

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
        List<ButtonEnum> row1 = List.of(ButtonEnum.NOTIFICATION_SETTINGS);
        List<ButtonEnum> row2 = List.of(ButtonEnum.DATE_OF_BIRTH_SETTINGS);
        List<ButtonEnum> row3 = List.of(ButtonEnum.TO_MAIN);

        return InlineKeyboardMarkup.builder()
                .keyboardRow(getKeyboardRow(row1))
                .keyboardRow(getKeyboardRow(row2))
                .keyboardRow(getKeyboardRow(row3))
                .build();
    }

    public InlineKeyboardMarkup getNotificationKeyboard() {
        Long chatId = updateContext.getChatId();
        List<ButtonEnum> row1 = List.of(getBirthdaySwitchButton(chatId));
        List<ButtonEnum> row2 = List.of(getHolidaySwitchButton(chatId));
        List<ButtonEnum> row3 = List.of(getDailySwitchButton(chatId));
        List<ButtonEnum> row4 = List.of(ButtonEnum.BACK_SETTINGS);

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

    public InlineKeyboardMarkup getInfoKeyboard() {
        List<ButtonEnum> row1 = List.of(ButtonEnum.INFO_WHAT_CAN_DO);
        List<ButtonEnum> row2 = List.of(ButtonEnum.INFO_SETTINGS);
        List<ButtonEnum> row3 = List.of(ButtonEnum.TO_MAIN);

        return InlineKeyboardMarkup.builder()
                .keyboardRow(getKeyboardRow(row1))
                .keyboardRow(getKeyboardRow(row2))
                .keyboardRow(getKeyboardRow(row3))
                .build();
    }

    public InlineKeyboardMarkup getBackInfoKeyboard() {
        List<ButtonEnum> row1 = List.of(ButtonEnum.BACK_INFO);

        return InlineKeyboardMarkup.builder()
                .keyboardRow(getKeyboardRow(row1))
                .build();
    }

    public InlineKeyboardMarkup getChatUsersKeyboard() {
//        List<User> users = updateContext.getUpdate().getChannelPost().getNewChatMembers();
        List<Account> accounts = accountService.getChatAccounts(updateContext.getChatId());

        List<List<InlineKeyboardButton>> rowList = accounts.stream()
                .map(acc -> getButtonRowWithUserName(acc, CallbackQueryType.SET_USER_BIRTH_DATE))
                .collect(Collectors.toList());
        rowList.add(getKeyboardRow(List.of(ButtonEnum.BACK_SETTINGS)));


        return new InlineKeyboardMarkup(rowList);
    }

    public InlineKeyboardMarkup getBackSettingsKeyboard() {
        List<ButtonEnum> row1 = List.of(ButtonEnum.BACK_SETTINGS);

        return InlineKeyboardMarkup.builder()
                .keyboardRow(getKeyboardRow(row1))
                .build();
    }

    public InlineKeyboardMarkup getSingleButtonKeyboard(ButtonEnum buttonEnum) {
        List<ButtonEnum> row1 = List.of(buttonEnum);

        return InlineKeyboardMarkup.builder()
                .keyboardRow(getKeyboardRow(row1))
                .build();

    }

    private List<InlineKeyboardButton> getButtonRowWithUserName(Account account, CallbackQueryType data){
        InlineKeyboardButton button = InlineKeyboardButton.builder()
                .text(account.getUserName())
                .callbackData(data.getCommandToString() + "&" + account.getTelegramId().toString())
                .build();

        return List.of(button);
    }

    public InlineKeyboardMarkup getBirthDateCancelButton(ButtonEnum buttonEnum, Long key) {
        InlineKeyboardButton button = InlineKeyboardButton.builder()
                .text(buttonEnum.getText())
                .callbackData(buttonEnum.getCommand() + "&" + key.toString())
                .build();

        return InlineKeyboardMarkup.builder()
                .keyboardRow(List.of(button))
                .build();
    }

    private List<InlineKeyboardButton> getKeyboardRow(List<ButtonEnum> buttonEnums) {
        return buttonEnums.stream()
                .map(ButtonEnum::renderButton)
                .collect(Collectors.toList());
    }

}
