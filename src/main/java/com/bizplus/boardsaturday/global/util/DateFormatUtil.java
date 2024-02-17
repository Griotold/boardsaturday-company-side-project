package com.bizplus.boardsaturday.global.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateFormatUtil {

    public static String toStringFormat(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
