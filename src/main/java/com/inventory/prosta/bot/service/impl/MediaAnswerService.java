package com.inventory.prosta.bot.service.impl;

import com.inventory.prosta.bot.Context.AnswerContext;
import com.inventory.prosta.bot.model.answer.MediaAnswerEvent;
import com.inventory.prosta.bot.repository.MediaRepo;
import com.inventory.prosta.bot.service.api.AccountService;
import com.inventory.prosta.bot.service.api.MessageService;
import com.inventory.prosta.bot.telegram.TelegramBotContext;
import com.inventory.prosta.bot.telegram.keyboard.InlineKeyboardBuilder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;
import jooq.tables.pojos.Media;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Service
@Slf4j
@RequiredArgsConstructor
public class MediaAnswerService extends AbstractAnswerService<MediaAnswerEvent>{

    private final AccountService accountService;
    private final AnswerContext<MediaAnswerEvent> answerContext;
    private final MessageService messageService;
    private final TelegramBotContext telegramBotContext;
    private final InlineKeyboardBuilder inlineKeyboardBuilder;
    private final MediaRepo mediaRepo;

    private final String ERROR_TEXT = "⚠️Картинка не сохранена.\n" +
            "\nПовторите снова или загрузите другую картинку\n";

    private final String SUCCESS_TEXT = "Изображение сохранено";

    public void createMediaAnswer(Media media) {
        Long creatorId = updateContext.getUpdate().getCallbackQuery().getFrom().getId();

        var event = MediaAnswerEvent.builder()
                .createDate(LocalDateTime.now())
                .chatId(updateContext.getChatId())
                .userid(creatorId)
                .messageId(updateContext.getMessageId())
                .media(media)
                .onSaveMedia(mediaRepo::saveMedia)
                .build();

        answerContext.store(creatorId, event);
        log.info("AnswerContext created {}.", LocalDateTime.now());
    }

    private Media createMedia() {
        return new Media();
    }

    @Override
    public boolean eventIsAnswer(MediaAnswerEvent answerEvent) {
        return false;
    }

    @Override
    public void executeAnswerEvent(MediaAnswerEvent answerEvent) {
        List<PhotoSize> photos = updateContext.getUpdate().getMessage().getPhoto();

        String fileId = photos.get(photos.size() - 1).getFileId();

        GetFile getFile = new GetFile();

//        File file = new File(filePath);
//        byte[] bytes = new byte[(int) file.length()];
//        FileInputStream fis = null;
//        try {
//            fis = new FileInputStream(file);
//            fis.read(bytes);
//        } finally {
//            if (fis != null) {
//                fis.close();
//            }
//        }



//        GetFile getFile = new GetFile();
//        getFile.setFileId(photos.get(0).getFileId());
//        File file = getFile.deserializeResponse();
//        org.telegram.telegrambots.api.objects.File file = getFile(getFile);
//
//        File f_path = new File("https://api.telegram.org/file/bot"+getBotToken()+"/"+file.getFilePath());
//
//        String caption = "file_id: " + f_id + "\nfile_path: " + f_path;
//
//        successMessage(birthdayAnswerEvent.getAccount().getUserName(), text);

    }

    @Override
    protected boolean chatIdCheck(MediaAnswerEvent answerEvent) {
        return updateContext.getChatId().equals(answerEvent.getChatId());
    }

    @Override
    protected boolean UserIdCheck(Update update, MediaAnswerEvent answerEvent) {
        return update.getMessage()
                .getFrom()
                .getId()
                .equals(answerEvent.getUserid());
    }

    @Override
    protected boolean logicCheck() {
        return updateContext.getUpdate().getMessage().hasPhoto();
    }


    @Override
    protected void errorMessage(MediaAnswerEvent answerEvent) {

    }

    @Override
    protected void successMessage(String accountName, String date) {

    }
}
