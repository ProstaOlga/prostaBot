package com.inventory.prosta.bot.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceBundleUtil {
    private final static String baseName = "message";
    private final static String languageCode = System.getenv("LANGUAGE_CODE");
    private final static String countryCode = System.getenv("COUNTRY_CODE");

    private static final ResourceBundle resourceBundle;

    static {
        resourceBundle = ResourceBundle.getBundle(baseName, new Locale(languageCode, countryCode));
    }

    public static String getMessageText(String key) {
        return resourceBundle.getString(key);
    }
}
