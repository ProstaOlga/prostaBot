package com.inventory.prosta.bot.service.comands;
import com.inventory.prosta.bot.model.UpdateContext;
import com.inventory.prosta.bot.model.enums.ButtonEnum;
import com.inventory.prosta.bot.service.api.ChatService;
import com.inventory.prosta.bot.telegram.keyboard.InlineKeyboardBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;

@Component
@RequiredArgsConstructor
public class HolidaySwitchCommand implements Command {

    private final ChatService chatService;
    private final UpdateContext updateContext;
    private final InlineKeyboardBuilder inlineKeyboardBuilder;

    @Override
    public BotApiMethod<?> execute() {
        Long chatId = updateContext.getChatId();
        String command = updateContext.getUpdate().getCallbackQuery().getData();
        Integer messageId = updateContext.getMessageId();

        return command.equals(ButtonEnum.HOLIDAY_CONGRATULATION_DISABLE.getCommand()) ?
                disableHolidayNotice(chatId, messageId)
                : enableHolidayNotice(chatId, messageId);
    }

    private EditMessageReplyMarkup disableHolidayNotice(Long chatId, Integer messageId){
        chatService.offHolidayNotice(chatId);

        return EditMessageReplyMarkup.builder()
                .chatId(chatId)
                .messageId(messageId)
                .replyMarkup(inlineKeyboardBuilder.getNotificationKeyboard())
                .build();
    }

    private EditMessageReplyMarkup enableHolidayNotice(Long chatId, Integer messageId){
        chatService.onHolidayNotice(chatId);

        return EditMessageReplyMarkup.builder()
                .chatId(chatId)
                .messageId(messageId)
                .replyMarkup(inlineKeyboardBuilder.getNotificationKeyboard())
                .build();
    }
}
