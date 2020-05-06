package ru.bookstore.core.enums;

import lombok.Getter;

@Getter
public enum ReportTemplatePath {
    CATEGORY_BOOKS_COUNT_TEMPLATE("reports/excel/count_available_books.xlsx");

    private String path;

    ReportTemplatePath(String path) {
        this.path = path;
    }
}
