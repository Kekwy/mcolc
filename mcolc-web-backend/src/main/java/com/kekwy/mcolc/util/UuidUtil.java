package com.kekwy.mcolc.util;

public class UuidUtil {

    public static String format(String uuid) {
        String s = uuid.replaceAll("-", "");
        return String.format("%s-%s-%s-%s-%s",
                s.substring(0, 8),
                s.substring(8, 12),
                s.substring(12, 16),
                s.substring(16, 20),
                s.substring(20));
    }

}
