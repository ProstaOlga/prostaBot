package com.inventory.prosta.bot.service.api;

import com.inventory.prosta.bot.BaseUnitTest;
import com.inventory.prosta.bot.model.enums.MediaFormat;
import com.inventory.prosta.bot.service.impl.MessageServiceImpl;
import com.inventory.prosta.bot.telegram.TelegramBotContext;
import jooq.tables.pojos.Media;
import net.bytebuddy.utility.RandomString;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.send.SendVideo;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.InputFile;

import java.time.LocalDate;
import java.util.UUID;

class MessageServiceTest extends BaseUnitTest {

    @Mock
    private MediaService mediaService;
    @Mock
    private TelegramBotContext telegramBotContext;

    @Test
    void sendMediaToChatImage() {
        MessageService messageService = new MessageServiceImpl(mediaService, telegramBotContext);

        Mockito.when(mediaService.mediaToInputFile(ArgumentMatchers.any(Media.class)))
                .thenReturn(new InputFile());

        Long chatId = 123L;
        Media media = new Media();
        media.setId(UUID.randomUUID());
        media.setMediaFormat(MediaFormat.JPG.getFormat());

        messageService.sendMediaToChat(chatId, media);

        Mockito.verify(mediaService, Mockito.times(1)).mediaToInputFile(media);
        Mockito.verify(telegramBotContext, Mockito.times(1)).execute(ArgumentMatchers.any(SendPhoto.class));
        Mockito.verify(telegramBotContext).execute(ArgumentMatchers.argThat(
                (SendPhoto sendPhoto) ->
                        sendPhoto.getChatId().equals(String.valueOf(chatId))
                                && sendPhoto.getCaption().equals(" "))
        );
        Mockito.verify(mediaService, Mockito.times(1)).addToMediaChatTable(
                ArgumentMatchers.eq(media.getId()),
                ArgumentMatchers.eq(chatId),
                ArgumentMatchers.any(LocalDate.class)
        );
    }

    @Test
    void sendMediaToChatVideo() {
        MessageService messageService = new MessageServiceImpl(mediaService, telegramBotContext);

        Mockito.when(mediaService.mediaToInputFile(ArgumentMatchers.any(Media.class)))
                .thenReturn(new InputFile());

        Long chatId = 123L;
        Media media = new Media();
        media.setId(UUID.randomUUID());
        media.setMediaFormat(MediaFormat.GIF.getFormat());

        messageService.sendMediaToChat(chatId, media);

        Mockito.verify(mediaService, Mockito.times(1)).mediaToInputFile(media);
        Mockito.verify(telegramBotContext, Mockito.times(1)).execute(ArgumentMatchers.any(SendVideo.class));
        Mockito.verify(telegramBotContext).execute(ArgumentMatchers.argThat(
                (SendVideo sendVideo) ->
                        sendVideo.getChatId().equals(String.valueOf(chatId))
                                && sendVideo.getCaption().equals(" "))
        );
        Mockito.verify(mediaService, Mockito.times(1)).addToMediaChatTable(
                ArgumentMatchers.eq(media.getId()),
                ArgumentMatchers.eq(chatId),
                ArgumentMatchers.any(LocalDate.class)
        );
    }

    @Test
    void testSendMediaToChatImage() {
        MessageService messageService = new MessageServiceImpl(mediaService, telegramBotContext);

        Mockito.when(mediaService.mediaToInputFile(ArgumentMatchers.any(Media.class)))
                .thenReturn(new InputFile());

        Long chatId = 123L;
        String text = RandomString.make(16);
        Media media = new Media();
        media.setId(UUID.randomUUID());
        media.setMediaFormat(MediaFormat.JPG.getFormat());

        messageService.sendMediaToChat(chatId, media, text);

        Mockito.verify(mediaService, Mockito.times(1)).mediaToInputFile(media);
        Mockito.verify(telegramBotContext, Mockito.times(1)).execute(ArgumentMatchers.any(SendPhoto.class));
        Mockito.verify(telegramBotContext).execute(ArgumentMatchers.argThat(
                (SendPhoto sendPhoto) ->
                        sendPhoto.getChatId().equals(String.valueOf(chatId))
                                && sendPhoto.getCaption().equals(text))
        );
        Mockito.verify(mediaService, Mockito.times(1)).addToMediaChatTable(
                ArgumentMatchers.eq(media.getId()),
                ArgumentMatchers.eq(chatId),
                ArgumentMatchers.any(LocalDate.class)
        );
    }

    @Test
    void testSendMediaToChatVideo() {
        MessageService messageService = new MessageServiceImpl(mediaService, telegramBotContext);

        Mockito.when(mediaService.mediaToInputFile(ArgumentMatchers.any(Media.class)))
                .thenReturn(new InputFile());

        Long chatId = 123L;
        String text = RandomString.make(16);
        Media media = new Media();
        media.setId(UUID.randomUUID());
        media.setMediaFormat(MediaFormat.GIF.getFormat());

        messageService.sendMediaToChat(chatId, media, text);

        Mockito.verify(mediaService, Mockito.times(1)).mediaToInputFile(media);
        Mockito.verify(telegramBotContext, Mockito.times(1)).execute(ArgumentMatchers.any(SendVideo.class));
        Mockito.verify(telegramBotContext).execute(ArgumentMatchers.argThat(
                (SendVideo sendVideo) ->
                        sendVideo.getChatId().equals(String.valueOf(chatId))
                                && sendVideo.getCaption().equals(text))
        );
        Mockito.verify(mediaService, Mockito.times(1)).addToMediaChatTable(
                ArgumentMatchers.eq(media.getId()),
                ArgumentMatchers.eq(chatId),
                ArgumentMatchers.any(LocalDate.class)
        );
    }

    @Test
    void sendMessageToChat() {
        MessageService messageService = new MessageServiceImpl(mediaService, telegramBotContext);

        Long chatId = RandomUtils.nextLong();
        String text = RandomString.make(16);

        messageService.sendMessageToChat(chatId, text);

        Mockito.verify(telegramBotContext, Mockito.times(1)).execute(ArgumentMatchers.any(SendMessage.class));
        Mockito.verify(telegramBotContext).execute(ArgumentMatchers.argThat(
                (SendMessage sendMessage) ->
                        sendMessage.getChatId().equals(String.valueOf(chatId))
                                && sendMessage.getText().equals(text))
        );
    }

    @Test
    void deleteMessage() {
        MessageService messageService = new MessageServiceImpl(mediaService, telegramBotContext);

        Long chatId = RandomUtils.nextLong();
        Integer messageId = RandomUtils.nextInt();

        messageService.deleteMessage(chatId, messageId);

        Mockito.verify(telegramBotContext, Mockito.times(1)).execute(ArgumentMatchers.any(DeleteMessage.class));
        Mockito.verify(telegramBotContext).execute(ArgumentMatchers.argThat(
                (DeleteMessage deleteMessage) ->
                        deleteMessage.getChatId().equals(String.valueOf(chatId))
                                && deleteMessage.getMessageId().equals(messageId))
        );
    }

    @Test
    void testSendMessageToChat() {
        MessageService messageService = new MessageServiceImpl(mediaService, telegramBotContext);

        SendMessage sendMessage = new SendMessage();

        messageService.sendMessageToChat(sendMessage);

        Mockito.verify(telegramBotContext, Mockito.times(1)).execute(ArgumentMatchers.any(SendMessage.class));

        Mockito.verify(telegramBotContext).execute(ArgumentMatchers.argThat(
                (SendMessage message) ->
                        message.equals(sendMessage))
        );

    }
}