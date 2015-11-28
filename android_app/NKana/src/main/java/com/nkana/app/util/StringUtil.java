package com.nkana.app.util;

/**
 * Created by chokkar
 */
public class StringUtil {

    public static boolean isNullOrEmpty(String input) {
        return input == null || input.trim().length() == 0;
    }
}
