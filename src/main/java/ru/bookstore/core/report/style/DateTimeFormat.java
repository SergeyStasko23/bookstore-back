package ru.bookstore.core.report.style;

import lombok.Getter;

@Getter
public enum DateTimeFormat {
    TIME_FORMAT("hh:mm"),
    DATE_FORMAT("dd.mm.yyyy"),
    DATE_TIME_FORMAT("dd.mm.yyyy hh:mm");

    private String format;

    DateTimeFormat(String format) {
        this.format = format;
    }
}
