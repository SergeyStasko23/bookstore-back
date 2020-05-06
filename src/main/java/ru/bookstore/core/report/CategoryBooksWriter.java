package ru.bookstore.core.report;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import ru.bookstore.business.entity.CategoryBooksCount;
import ru.bookstore.business.entity.CategoryBooksReport;
import ru.bookstore.business.entity.CategoryBooksTotalCost;

import java.util.LinkedHashMap;
import java.util.Map;

import static ru.bookstore.core.enums.ReportTemplatePath.CATEGORY_BOOKS_COUNT_TEMPLATE;

@Component
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CategoryBooksWriter<T extends CategoryBooksReport> extends AbstractExcelBuilder<T> {
    private static final String FIRST_SHEET = "Количество имеющихся книг";
    private static final String[] FIRST_SHEET_TITLES = {"Категория", "Количество книг"};

    private static final String SECOND_SHEET = "Общая стоимость книг";
    private static final String[] SECOND_SHEET_TITLES = {"Категория", "Общая стоимость"};

    @Override
    protected Map<String, String[]> getSheetTitlesMap() {
        Map<String, String[]> sheetTitlesMap = new LinkedHashMap<>();
        sheetTitlesMap.put(FIRST_SHEET, FIRST_SHEET_TITLES);
        sheetTitlesMap.put(SECOND_SHEET, SECOND_SHEET_TITLES);
        return sheetTitlesMap;
    }

    @Override
    protected void fillRow(T item) {
        if (item instanceof CategoryBooksCount) {
            CategoryBooksCount count = (CategoryBooksCount) item;
            write(count.getCategoryName());
            write(count.getBooksCount());
        }
        if (item instanceof CategoryBooksTotalCost) {
            CategoryBooksTotalCost totalCost = (CategoryBooksTotalCost) item;
            write(totalCost.getCategoryName());
            write(totalCost.getTotalCost());
        }
    }

    @Override
    protected String getReportTemplatePath() {
        return CATEGORY_BOOKS_COUNT_TEMPLATE.getPath();
    }

    @Override
    public String getFileName() {
        return "count_available_books.xlsx";
    }
}
