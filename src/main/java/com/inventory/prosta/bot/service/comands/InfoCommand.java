package com.inventory.prosta.bot.service.comands;

import com.inventory.prosta.bot.model.enums.ButtonEnum;
import com.inventory.prosta.bot.telegram.keyboard.InlineKeyboardBuilder;
import com.inventory.prosta.bot.util.ResourceBundleUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import com.inventory.prosta.bot.model.UpdateContext;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Component
@RequiredArgsConstructor
public class InfoCommand implements Command{
//    @Value("${message.info}")
    private final static String TEXT_MAIN = ResourceBundleUtil.getMessageText("info.main");
//    @Value("${message.info.what.can.do}")
    private final static String TEXT_FUNCTIONAL = ResourceBundleUtil.getMessageText("info.functional");
//    @Value("${message.info.settings}")
    private final static String TEXT_SETTINGS = ResourceBundleUtil.getMessageText("info.settings");

    private final UpdateContext updateContext;
    private final InlineKeyboardBuilder inlineKeyboardBuilder;


    @Override
    public BotApiMethod<?> execute() {
        Long chatId = updateContext.getChatId();
        Integer messageId = updateContext.getUpdate().getCallbackQuery().getMessage().getMessageId();
        ButtonEnum command = ButtonEnum.getButtonEnumFromString(updateContext.getUpdate().getCallbackQuery().getData());

        switch (command){
            case INFO_BOT_FUNCTION:
                return whatCanDo(chatId, messageId);
            case INFO_SETTINGS:
                return settingsInfo(chatId, messageId);
            case INFO:
            default:
                return info(chatId, messageId);
        }
    }

    private BotApiMethod<?> info(Long chatId, Integer messageId){
        return EditMessageText.builder()
                .chatId(chatId)
                .messageId(messageId)
                .text(TEXT_MAIN)
                .replyMarkup(inlineKeyboardBuilder.getInfoKeyboard())
                .build();
    }

    private BotApiMethod<?> whatCanDo(Long chatId, Integer messageId){
        return EditMessageText.builder()
                .chatId(chatId)
                .messageId(messageId)
                .text(TEXT_FUNCTIONAL)
                .replyMarkup(inlineKeyboardBuilder.getBackInfoKeyboard())
                .build();
    }

    private BotApiMethod<?> settingsInfo(Long chatId, Integer messageId){
        return EditMessageText.builder()
                .chatId(chatId)
                .messageId(messageId)
                .text(TEXT_SETTINGS)
                .replyMarkup(inlineKeyboardBuilder.getBackInfoKeyboard())
                .build();
    }
}
