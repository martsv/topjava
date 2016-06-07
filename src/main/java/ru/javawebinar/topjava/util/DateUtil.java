package ru.javawebinar.topjava.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by mart on 06.06.16.
 */
public final class DateUtil {
    private DateUtil() {}

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    public static String formatDateTime(LocalDateTime dateTime) {
        return dateTime == null? "": dateTime.format(dateTimeFormatter);
    }

    public static String formatDateTimeWithPattern(LocalDateTime dateTime, String pattern) {
        return dateTime == null? "": dateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    public static LocalDateTime parseDateTime(String strDate) {
        return LocalDateTime.parse(strDate, dateTimeFormatter);
    }
}
