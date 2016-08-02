package ru.javawebinar.topjava.util.converter;

import org.springframework.format.Formatter;
import ru.javawebinar.topjava.util.TimeUtil;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Created by mart on 01.08.16.
 */
public class LocalDateTimeFormatter implements Formatter<LocalDateTime> {
    @Override
    public LocalDateTime parse(String text, Locale locale) throws ParseException {
        return TimeUtil.parseLocalDateTime(text);
    }

    @Override
    public String print(LocalDateTime dateTime, Locale locale) {
        return TimeUtil.toString(dateTime);
    }
}
