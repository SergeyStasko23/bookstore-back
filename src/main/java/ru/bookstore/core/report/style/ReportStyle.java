package ru.bookstore.core.report.style;

import org.apache.poi.ss.usermodel.*;

import static ru.bookstore.core.report.style.DateTimeFormat.*;

public class ReportStyle {
    private Workbook workbook;
    private CreationHelper creationHelper;

    private static final String FONT_NAME = "Arial";
    private static final short FONT_SIZE = 12;

    public ReportStyle(Workbook workbook, CreationHelper creationHelper) {
        this.workbook = workbook;
        this.creationHelper = creationHelper;
    }

    public CellStyle getDateTimeStyle() {
        CellStyle dateTimeStyle = workbook.createCellStyle();
        short dataFormat = creationHelper.createDataFormat().getFormat(DATE_TIME_FORMAT.getFormat());
        dateTimeStyle.setDataFormat(dataFormat);
        dateTimeStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        dateTimeStyle.setAlignment(HorizontalAlignment.CENTER);
        dateTimeStyle.setBorderBottom(BorderStyle.THIN);
        dateTimeStyle.setBorderTop(BorderStyle.THIN);
        dateTimeStyle.setBorderLeft(BorderStyle.THIN);
        dateTimeStyle.setBorderRight(BorderStyle.THIN);
        dateTimeStyle.setWrapText(true);
        return dateTimeStyle;
    }

    public CellStyle getDateStyle() {
        CellStyle dateStyle = workbook.createCellStyle();
        short dataFormat = creationHelper.createDataFormat().getFormat(DATE_FORMAT.getFormat());
        dateStyle.setDataFormat(dataFormat);
        dateStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        dateStyle.setAlignment(HorizontalAlignment.CENTER);
        dateStyle.setBorderBottom(BorderStyle.THIN);
        dateStyle.setBorderTop(BorderStyle.THIN);
        dateStyle.setBorderLeft(BorderStyle.THIN);
        dateStyle.setBorderRight(BorderStyle.THIN);
        dateStyle.setWrapText(true);
        return dateStyle;
    }

    public CellStyle getTimeStyle() {
        CellStyle timeStyle = workbook.createCellStyle();
        short dataFormat = creationHelper.createDataFormat().getFormat(TIME_FORMAT.getFormat());
        timeStyle.setDataFormat(dataFormat);
        timeStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        timeStyle.setAlignment(HorizontalAlignment.CENTER);
        timeStyle.setBorderBottom(BorderStyle.THIN);
        timeStyle.setBorderTop(BorderStyle.THIN);
        timeStyle.setBorderLeft(BorderStyle.THIN);
        timeStyle.setBorderRight(BorderStyle.THIN);
        timeStyle.setWrapText(true);
        return timeStyle;
    }

    public CellStyle getCellStyle(boolean isHeader) {
        Font font = workbook.createFont();
        font.setFontName(FONT_NAME);
        font.setFontHeightInPoints(FONT_SIZE);

        CellStyle cellStyle = workbook.createCellStyle();
        if (isHeader) {
            font.setBold(true);
            cellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        }
        cellStyle.setFont(font);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setWrapText(true);
        return cellStyle;
    }
}
