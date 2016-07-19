package ru.javawebinar.topjava.web;

import org.springframework.format.Formatter;
import ru.javawebinar.topjava.util.TimeUtil;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Created by mart on 19.07.16.
 */
public class DateFormatter implements Formatter<LocalDate> {
    @Override
    public LocalDate parse(String strDate, Locale locale) throws ParseException {
        return TimeUtil.parseLocalDate(strDate);
    }

    @Override
    public String print(LocalDate ld, Locale locale) {
        return ld.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }
}
