package com.polly.program.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
    public static final String EMPTY_STRING = "";

    public static boolean isBlank(String str) {
        return str == null || str.length() < 1;
    }

    public static boolean isTrimBlank(String str) {
        return str == null || str.equals(EMPTY_STRING) || str.length() < 1;
    }

    /**
     * 对字符串进行过滤,去除\r\n
     */
    public static String filter(String str) {
        return str.replace("\\r\\n", "");
    }

    public static boolean isMobile(String phoneNumber) {
        String expression = "^[1]\\d{10}$";
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

}
