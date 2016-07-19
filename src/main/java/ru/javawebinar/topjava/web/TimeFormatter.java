package ru.javawebinar.topjava.web;

import org.springframework.format.Formatter;
import ru.javawebinar.topjava.util.TimeUtil;

import java.text.ParseException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Created by mart on 19.07.16.
 */
public class TimeFormatter implements Formatter<LocalTime> {
    @Override
    public LocalTime parse(String strTime, Locale locale) throws ParseException {
        return TimeUtil.parseLocalTime(strTime);
    }

    @Override
    public String print(LocalTime ld, Locale locale) {
        return ld.format(DateTimeFormatter.ISO_LOCAL_TIME);
    }
}

