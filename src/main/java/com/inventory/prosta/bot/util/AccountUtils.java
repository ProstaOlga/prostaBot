package com.inventory.prosta.bot.util;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.User;
import jooq.tables.pojos.Account;

@Getter
@Setter
public class AccountUtils {

    public static String getAccountName(User user){
        String userName = user.getUserName();
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        Long userId = user.getId();

        return userName == null ? fillUserName(firstName, lastName, userId) : userName;
    }

    public static String getAccountName(Account account){
        String userName = account.getUserName();
        String firstName = account.getFirstName();
        String lastName = account.getLastName();
        Long userId = account.getTelegramId();

        return userName == null ? fillUserName(firstName, lastName, userId) : userName;
    }

    private static String fillUserName(String firstName, String lastName, Long userId){
        return isFullName(firstName, lastName) ? getFullName(firstName, lastName) : userId.toString();
    }

    private static boolean isFullName(String firstName, String lastName){
        return firstName != null | lastName != null;
    }

    private static String getFullName(String firstName, String lastName) {
        if (firstName != null && lastName != null){
            return firstName + " " + lastName.charAt(0) + ".";
        }
        else {
            return firstName == null ? lastName : firstName;
        }
    }


}
