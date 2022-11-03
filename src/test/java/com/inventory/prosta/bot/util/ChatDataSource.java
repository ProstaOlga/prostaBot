package com.inventory.prosta.bot.util;

import java.util.ArrayList;
import java.util.List;
import jooq.tables.pojos.ChatDb;
import net.bytebuddy.utility.RandomString;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.boot.test.context.TestComponent;

@TestComponent
public class ChatDataSource implements DataSource<ChatDb>{

    @Override
    public  List<ChatDb> getList(ChatDb instance, int size){
        List<ChatDb> result = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            ChatDb chat = new ChatDb();
            chat.setChatId(RandomUtils.nextLong());
            chat.setTitle(RandomString.make(16));
            chat.setChatType(instance.getChatType());
            chat.setBirthdayNotice(instance.getBirthdayNotice());
            chat.setDailyNotice(instance.getDailyNotice());
            chat.setHolidayNotice(instance.getHolidayNotice());
            result.add(chat);
        }

        return result;
    }

    @Override
    public ChatDb getSingleInstance() {
        ChatDb chat = new ChatDb();

        chat.setChatId(RandomUtils.nextLong());
        chat.setTitle(RandomString.make(16));
        chat.setChatType("supergroup");
        chat.setBirthdayNotice(true);
        chat.setDailyNotice(true);
        chat.setHolidayNotice(true);

        return chat;
    }
}
