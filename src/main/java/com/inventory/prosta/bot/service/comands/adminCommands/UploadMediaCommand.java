package com.inventory.prosta.bot.service.comands.adminCommands;

import com.inventory.prosta.bot.Context.AnswerContext;
import com.inventory.prosta.bot.model.UpdateContext;
import com.inventory.prosta.bot.model.answer.MediaAnswerEvent;
import com.inventory.prosta.bot.model.enums.ButtonEnum;
import com.inventory.prosta.bot.service.comands.Command;
import com.inventory.prosta.bot.service.impl.MediaAnswerService;
import com.inventory.prosta.bot.telegram.keyboard.InlineKeyboardBuilder;
import com.inventory.prosta.bot.util.TextParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import jooq.tables.pojos.Media;

@Component
@RequiredArgsConstructor
public class UploadMediaCommand implements Command {

    private final TextParser textParser;
    private final UpdateContext updateContext;
    private final InlineKeyboardBuilder inlineKeyboardBuilder;
    private final MediaAnswerService mediaAnswerService;
    private final AnswerContext<MediaAnswerEvent> answerContext;

    private final static String TEXT_SELECT_MEDIA_TYPE = "Выберите тип изображения";
    private final static String TEXT_UPLOAD = "Загрузите изображение типа %s";

    @Override
    public BotApiMethod<?> execute() {
        String command = textParser.parseDataText(updateContext.getUpdate().getCallbackQuery().getData());

        return command.equals(ButtonEnum.ADMIN_MEDIA_SELECT_TYPE.getCommand())
                ? selectType()
                : command.equals(ButtonEnum.ADMIN_MEDIA_CANCEL_ANSWER_EVENT.getCommand())
                ? cancelMediaAnswerEvent(Long.parseLong(textParser.getDataParams(updateContext.getUpdate().getCallbackQuery().getData())))
                : createMediaAnswerEvent();
    }

    private BotApiMethod<?> selectType() {
        Long chatId = updateContext.getChatId();

        return EditMessageText.builder()
                .chatId(chatId)
                .messageId(updateContext.getMessageId())
                .text(TEXT_SELECT_MEDIA_TYPE)
                .replyMarkup(inlineKeyboardBuilder.getMediaTypeKeyboard())
                .build();

    }

    private BotApiMethod<?> createMediaAnswerEvent() {
        String data = updateContext.getUpdate().getCallbackQuery().getData();
        String mediaType = textParser.getDataParams(data);
        mediaAnswerService.createMediaAnswer(createMedia(mediaType));

        return EditMessageText.builder()
                .messageId(updateContext.getMessageId())
                .chatId(updateContext.getChatId())
                .text(String.format(TEXT_UPLOAD, mediaType))
                .replyMarkup(inlineKeyboardBuilder.getCancelAnswerEventButton(ButtonEnum.ADMIN_MEDIA_CANCEL_ANSWER_EVENT, updateContext.getUserId()))
                .build();
    }

    private Media createMedia(String mediaType){
        Media media = new Media();
        media.setMediaFormat(".jpg");
        media.setMediaType(mediaType);

        return media;
    }

    private BotApiMethod<?> cancelMediaAnswerEvent(Long userIdKey) {
        answerContext.remove(userIdKey);

        return  EditMessageText.builder()
                .messageId(updateContext.getMessageId())
                .chatId(updateContext.getChatId())
                .text("Настройки администратора")
                .replyMarkup(inlineKeyboardBuilder.getAdminPageKeyboard())
                .build();
    }
}
