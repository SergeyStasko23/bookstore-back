package ru.bookstore.core.report;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;
import ru.bookstore.core.exception.ExcelWritingException;
import ru.bookstore.core.report.style.ReportStyle;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static ru.bookstore.business.util.DateUtils.convertToDate;

@Slf4j
public abstract class AbstractExcelBuilder<T> {
    private ReportStyle reportStyle;
    private Workbook workbook;
    private Sheet sheet;
    private Row row;
    private Cell cell;

    private int currentSheetIndex = 0;
    private int currentRowIndex = 1;
    private int currentColumnIndex = 0;

    private static final String FIRST_SHEET_DEFAULT_NAME = "Лист1";
    private static final int HEADER_ROW_INDEX = 0;

    public void build(List<List<T>> dataLists, OutputStream outputStream) throws IOException {
        try {
            workbook = initWorkbookAndReportStyle();
            createSheets(getSheetTitlesMap(), dataLists);
            hideDefaultSheet();
            writeWorkbook(outputStream);
        } catch (IOException e) {
            log.error(e.getMessage());
        } finally {
            workbook.close();
        }
    }

    private Workbook initWorkbookAndReportStyle() throws IOException {
        Workbook workbook = new XSSFWorkbook(new ClassPathResource(getReportTemplatePath()).getInputStream());
        reportStyle = new ReportStyle(workbook, workbook.getCreationHelper());
        return workbook;
    }

    private void createSheets(Map<String, String[]> sheetTitlesMap, List<List<T>> dataLists) {
        if (sheetTitlesMap.size() != dataLists.size()) {
            throw new ExcelWritingException(this.getClass());
        }
        sheetTitlesMap.forEach((sheetName, titles) -> {
            sheet = workbook.createSheet(sheetName);
            createHeaderRow(titles);
            writeDataToSheet(dataLists.get(currentSheetIndex));
            autoSizeColumn(titles);
        });
    }

    private void createHeaderRow(String[] titles) {
        Row headerRow = sheet.createRow(HEADER_ROW_INDEX);
        CellStyle headerCellStyle = reportStyle.getCellStyle(true);

        for (int columnIndex = 0; columnIndex < titles.length; columnIndex++) {
            Cell cell = headerRow.createCell(columnIndex);
            cell.setCellValue(titles[columnIndex]);
            cell.setCellStyle(headerCellStyle);
        }
    }

    private void writeDataToSheet(List<T> dataList) {
        dataList.forEach(item -> {
            nextRow();
            fillRow(item);
        });
        prepareToNextSheet();
    }

    private void prepareToNextSheet() {
        currentSheetIndex++;
        currentRowIndex = 1;
    }

    private void hideDefaultSheet() {
        int defaultSheetIndex = 0;
        String defaultSheetName = workbook.getSheetAt(defaultSheetIndex).getSheetName();
        if (defaultSheetName.equals(FIRST_SHEET_DEFAULT_NAME)) {
            workbook.removeSheetAt(defaultSheetIndex);
        }
    }

    private void writeWorkbook(OutputStream outputStream) throws IOException {
        workbook.write(outputStream);
        outputStream.flush();
    }

    private void nextRow() {
        row = sheet.createRow(currentRowIndex++);
        currentColumnIndex = 0;
    }

    private void nextColumn() {
        cell = createCell(currentColumnIndex++);
        cell.setCellStyle(reportStyle.getCellStyle(false));
    }

    private Cell createCell(int cellId) {
        return row.createCell(cellId);
    }

    private void autoSizeColumn(String[] titles) {
        for (int i = 0; i < titles.length; i++) {
            sheet.autoSizeColumn(i);
        }
    }

    void write(String value) {
        nextColumn();
        cell.setCellValue(value);
    }

    void write(BigDecimal value) {
        write(value.toString());
    }

    void write(Integer value) {
        nextColumn();
        cell.setCellValue(value);
    }

    void write(Long value) {
        nextColumn();
        cell.setCellValue(value);
    }

    void write(Float value) {
        nextColumn();
        cell.setCellValue(value);
    }

    void write(Double value) {
        nextColumn();
        cell.setCellValue(value);
    }

    void write(Boolean value) {
        if (value) {
            write("Да");
        } else {
            write("Нет");
        }
    }

    void write(LocalDate value) {
        nextColumn();
        cell.setCellValue(convertToDate(value));
        cell.setCellStyle(reportStyle.getDateStyle());
    }

    void write(LocalDateTime value) {
        nextColumn();
        cell.setCellValue(convertToDate(value));
        cell.setCellStyle(reportStyle.getDateTimeStyle());
    }

    protected abstract Map<String, String[]> getSheetTitlesMap();
    protected abstract void fillRow(T item);
    protected abstract String getReportTemplatePath();
    public abstract String getFileName();
}
