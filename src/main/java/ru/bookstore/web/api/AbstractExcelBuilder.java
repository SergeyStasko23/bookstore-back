//package ru.bookstore.web.api;
//
//import lombok.Getter;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.poi.ss.usermodel.BorderStyle;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.CellStyle;
//import org.apache.poi.ss.usermodel.CreationHelper;
//import org.apache.poi.ss.usermodel.DataFormat;
//import org.apache.poi.ss.usermodel.Font;
//import org.apache.poi.ss.usermodel.IndexedColors;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//
//import java.io.IOException;
//import java.io.OutputStream;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import static ru.homecredit.mobis.utils.date.DateUtils.convertToDate;
//
///**
// * @author ssyakovlev
// * date: 13.02.16
// */
//@Slf4j
//public abstract class AbstractExcelBuilder<T> implements ReportBuilder<T> {
//    public static final int XLS_ROWS_LIMIT = 65536;
//    private static final String DEFAULT_SHEET_TITLE = "Лист %s";
//
//    protected int firstHeaderColumnIdx = 0;
//    protected int lastHeaderColumnIdx = 0;
//
//    @Getter
//    private Workbook workbook;
//    private CreationHelper createHelper;
//    protected Sheet currentSheet;
//    protected Row currentRow;
//    protected Cell currentCell;
//
//    protected Style style;
//
//
//    protected int currentColumnIndex = 0;
//    protected int currentRowIndex = 0;
//    private int currentPageIndex;
//    protected String sheetTitleTemplate;
//
//
//    @Override
//    public void build(List<T> items, OutputStream outputStream) throws IOException {
//        try {
//            initWorkbook();
//
//            style = new Style(workbook);
//            afterStyleInit(style, workbook);
//
//            sheetTitleTemplate = DEFAULT_SHEET_TITLE;
//            initCurrentSheet(workbook);
//            afterSheetInit(currentSheet);
//
//            for (T dto : items) {
//                nextRow();
//                fillRow(dto);
//            }
//            if (currentRowIndex > 0) {
//                customizeSheetAfterFill(currentSheet, items);
//            }
//            writeWorkbook(outputStream);
//        } catch (IOException e) {
//            log.error(e.getMessage(), e);
//        } finally {
//            workbook.close();
//        }
//    }
//
//    protected String getDateFormat() {
//        return "dd.mm.yyyy";
//    }
//
//    protected String getDateTimeFormat() {
//        return "dd.mm.yyyy hh:mm";
//    }
//
//    protected String getTimeFormat() {
//        return "hh:mm";
//    }
//
//    protected class Style {
//        private Map<String, CellStyle> styleMap;
//
//        public Style(Workbook workbook) {
//            styleMap = new HashMap<>(StyleName.values().length);
//
//            CellStyle defaultStyle = workbook.createCellStyle();
//            defaultStyle.setFont(buildDefaultFont(workbook));
//            defaultStyle.setBorderBottom(BorderStyle.THIN);
//            defaultStyle.setBorderTop(BorderStyle.THIN);
//            defaultStyle.setBorderLeft(BorderStyle.THIN);
//            defaultStyle.setBorderRight(BorderStyle.THIN);
//            defaultStyle.setWrapText(true);
//            styleMap.put(StyleName.DEFAULT.name(), defaultStyle);
//
//            CellStyle dateStyle = workbook.createCellStyle();
//            dateStyle.cloneStyleFrom(defaultStyle);
//            dateStyle.setDataFormat(createHelper.createDataFormat().getFormat(getDateFormat()));
//            styleMap.put(StyleName.DATE.name(), dateStyle);
//
//            CellStyle dateTimeStyle = workbook.createCellStyle();
//            dateTimeStyle.cloneStyleFrom(defaultStyle);
//            dateTimeStyle.setDataFormat(createHelper.createDataFormat().getFormat(getDateTimeFormat()));
//            styleMap.put(StyleName.DATETIME.name(), dateTimeStyle);
//
//            CellStyle timeStyle = workbook.createCellStyle();
//            timeStyle.cloneStyleFrom(defaultStyle);
//            timeStyle.setDataFormat(createHelper.createDataFormat().getFormat(getTimeFormat()));
//            styleMap.put(StyleName.TIME.name(), timeStyle);
//
//            DataFormat format = workbook.createDataFormat();
//
//            CellStyle numericStyle = workbook.createCellStyle();
//            numericStyle.cloneStyleFrom(defaultStyle);
//            numericStyle.setDataFormat(format.getFormat("0"));
//            styleMap.put(StyleName.NUMERIC.name(), numericStyle);
//
//            CellStyle redNumericStyle = workbook.createCellStyle();
//            redNumericStyle.cloneStyleFrom(numericStyle);
//            redNumericStyle.setFont(buildRedFont());
//            styleMap.put(StyleName.RED_NUMERIC.name(), redNumericStyle);
//        }
//
//        public void add(String styleName, CellStyle cellStyle) {
//            styleMap.put(styleName, cellStyle);
//        }
//
//        public CellStyle get(String styleName) {
//            return styleMap.get(styleName);
//        }
//
//        protected Font buildDefaultFont(Workbook workbook) {
//            Font defaultFont = workbook.createFont();
//            defaultFont.setFontHeightInPoints((short) 10);
//            defaultFont.setFontName("Arial");
//            defaultFont.setColor(IndexedColors.BLACK.getIndex());
//            return defaultFont;
//        }
//
//        private Font buildRedFont() {
//            Font font = buildDefaultFont(workbook);
//            font.setColor(IndexedColors.RED.getIndex());
//            return font;
//        }
//    }
//
//    protected enum StyleName {
//        DEFAULT, TIME, DATETIME, DATE, HEADER, NUMERIC, RED_NUMERIC
//    }
//
//    protected void afterStyleInit(Style style, Workbook workbook) {
//
//    }
//
//    protected abstract void afterSheetInit(Sheet currentSheet);
//
//    protected abstract void initCurrentSheet(Workbook workbook);
//
//
//    protected void writeWorkbook(OutputStream outputStream) throws IOException {
//        workbook.write(outputStream);
//        outputStream.flush();
//    }
//
//    protected void initWorkbook() throws IOException {
//        workbook = createWorkBook();
//        createHelper = workbook.getCreationHelper();
//        currentPageIndex = 0;
//    }
//
//
//    protected void nextSheet() {
//        currentSheet = workbook.createSheet(String.format(sheetTitleTemplate, currentPageIndex + 1));
//        currentPageIndex++;
//        currentRowIndex = 0;
//        currentColumnIndex = 0;
//        customizeSheetBeforeFill(currentSheet);
//    }
//
//    protected void nextRow() {
//        currentRow = currentSheet.createRow(currentRowIndex++);
//        currentColumnIndex = 0;
//
//        if (currentRowIndex >= XLS_ROWS_LIMIT) {
//            throw new MaxRowLimitExceededException();
//        }
//    }
//
//    protected void nextColumn() {
//        nextColumn(style.styleMap.get(StyleName.DEFAULT.name()));
//    }
//
//    protected void nextColumn(CellStyle cellStyle) {
//        currentCell = createCell(currentColumnIndex++, currentRow, cellStyle);
//    }
//
//    private Cell createCell(int cellId, Row row, CellStyle cellStyle) {
//        Cell cell = row.createCell(cellId);
//        cell.setCellStyle(cellStyle);
//        return cell;
//    }
//
//
//    protected void write(String value) {
//        nextColumn();
//        if (value != null) currentCell.setCellValue(value);
//    }
//
//    protected void write(Integer value) {
//        nextColumn(style.styleMap.get(StyleName.NUMERIC.name()));
//        if (value != null) currentCell.setCellValue(value);
//    }
//
//    protected void write(Long value) {
//        nextColumn(style.styleMap.get(StyleName.NUMERIC.name()));
//        if (value != null) currentCell.setCellValue(value);
//    }
//
//    protected void write(Double value) {
//        nextColumn(style.styleMap.get(StyleName.NUMERIC.name()));
//        if (value != null) currentCell.setCellValue(value);
//    }
//
//    protected void writeColored(Double value) {
//        if (value != null && value != 0) {
//            nextColumn(style.styleMap.get(StyleName.RED_NUMERIC.name()));
//        } else {
//            nextColumn(style.styleMap.get(StyleName.NUMERIC.name()));
//        }
//        if (value != null) currentCell.setCellValue(value);
//    }
//
//    protected void write(Date value) {
//        nextColumn(style.styleMap.get(StyleName.DATETIME.name()));
//        if (value != null) {
//            currentCell.setCellValue(value);
//        }
//    }
//
//    protected void write(LocalDateTime value) {
//        write(convertToDate(value));
//    }
//
//    protected void write(LocalDate value) {
//        writeDate(convertToDate(value));
//    }
//
//    protected void write(Boolean value) {
//        if (value == null) {
//            write("");
//            return;
//        }
//        if (value) {
//            write("Да");
//        } else {
//            write("Нет");
//        }
//    }
//
//    protected void writeDate(Date value) {
//        nextColumn(style.styleMap.get(StyleName.DATE.name()));
//        if (value != null) currentCell.setCellValue(value);
//    }
//
//    protected void writeDate(LocalDateTime dateTime) {
//        writeDate(convertToDate(dateTime));
//    }
//
//
//    protected abstract Workbook createWorkBook() throws IOException;
//
//
//    protected void customizeSheetBeforeFill(Sheet sheet) {
//
//    }
//
//    protected void customizeSheetAfterFill(Sheet sheet, List<T> items) {
//
//    }
//
//    protected void autoSizeColumns(Sheet sheet, Integer... columnIds) {
//        for (Integer columnId : columnIds) {
//            sheet.autoSizeColumn(columnId);
//        }
//    }
//
//    protected void autoSizeColumnsFor(Sheet sheet, int startColId, int endColId) {
//        int origColWidth;
//        for (int i = startColId; i <= endColId; i++) {
//            origColWidth = (int) (sheet.getRow(0).getCell(i).getStringCellValue().length() * 1.2) * 256 + 2000;
//            sheet.setColumnWidth(i, origColWidth);
//            sheet.autoSizeColumn(i);
//            if (origColWidth > sheet.getColumnWidth(i)) {
//                sheet.setColumnWidth(i, origColWidth);
//            }
//        }
//    }
//
//
//    protected abstract void fillRow(T dto);
//
//}
