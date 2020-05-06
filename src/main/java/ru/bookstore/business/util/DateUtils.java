package ru.bookstore.business.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

import static java.util.Optional.ofNullable;

public class DateUtils {
    public static Date convertToDate(LocalDate localDate) {
        return ofNullable(localDate)
                .map(date -> Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .orElse(null);
    }

    public static Date convertToDate(LocalDateTime localDateTime) {
        return ofNullable(localDateTime)
                .map(dateTime -> Date.from(dateTime.toInstant(ZoneOffset.UTC)))
                .orElse(null);
    }
}
