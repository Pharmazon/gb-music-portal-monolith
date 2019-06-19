package ru.geekbrains.musicportal.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ConcurrentDateFormat {

    private static final ThreadLocal<SimpleDateFormat> thread = ThreadLocal.withInitial(() ->
            new SimpleDateFormat("dd.MM.yyyy HH:mm:ss"));

    public static String format(Date date) {
        return thread.get().format(date);
    }

    public static Date parse(String source) throws ParseException {
        return thread.get().parse(source);
    }
}
