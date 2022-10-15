package com.inventory.prosta.bot.service.comands;

import com.inventory.prosta.bot.model.enums.ButtonEnum;
import com.inventory.prosta.bot.service.api.MessageService;
import com.inventory.prosta.bot.telegram.keyboard.InlineKeyboardBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import com.inventory.prosta.bot.model.UpdateContext;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Component
@RequiredArgsConstructor
public class InfoCommand implements Command{
//    @Value("${message.info}")
    private final static String infoText = "Привет,меня зовут ProstaBot и теперь я буду жить в твоем чатике. Что бы ты еще хотел узнать обо мне?";
//    @Value("${message.info.what.can.do}")
    private final static String whatCanDoText = "Чтобы перейти в главное меню введи в чате команду \"/bot\".\n\n" +
        "Я умею делать следующие вещи:\n\n" +
        "1)отправлять пожелания доброго утра/ночи;\n" +
        "2)поздравлять с праздниками;\n" +
        "4)отправлять котиков по запросу.\n\n" +
        " Для получения котика нажми \"Хочу котика\" в главном меню чата;";
//    @Value("${message.info.settings}")
    private final static String settingsInfoText = "Чтобы перейти в настройки введи в чате команду \"/settings\".\n" +
        "В настройках бота ты можешь настроить оповещения для всего чата.";

    private final UpdateContext updateContext;
    private final InlineKeyboardBuilder inlineKeyboardBuilder;


    @Override
    public BotApiMethod<?> execute() {
        Long chatId = updateContext.getChatId();
        Integer messageId = updateContext.getUpdate().getCallbackQuery().getMessage().getMessageId();
        ButtonEnum command = ButtonEnum.getButtonEnumFromString(updateContext.getUpdate().getCallbackQuery().getData());

        switch (command){
            case INFO_WHAT_CAN_DO:
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
                .text(infoText)
                .replyMarkup(inlineKeyboardBuilder.getInfoKeyboard())
                .build();
    }

    private BotApiMethod<?> whatCanDo(Long chatId, Integer messageId){
        return EditMessageText.builder()
                .chatId(chatId)
                .messageId(messageId)
                .text(whatCanDoText)
                .replyMarkup(inlineKeyboardBuilder.getBackInfoKeyboard())
                .build();
    }

    private BotApiMethod<?> settingsInfo(Long chatId, Integer messageId){
        return EditMessageText.builder()
                .chatId(chatId)
                .messageId(messageId)
                .text(settingsInfoText)
                .replyMarkup(inlineKeyboardBuilder.getBackInfoKeyboard())
                .build();
    }
}
