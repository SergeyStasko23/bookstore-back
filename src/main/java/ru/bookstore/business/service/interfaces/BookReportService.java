package ru.bookstore.business.service.interfaces;

import ru.bookstore.business.entity.CategoryBooksCount;
import ru.bookstore.business.entity.CategoryBooksTotalCost;

import java.util.List;

public interface BookReportService {
    List<CategoryBooksCount> getAllCategoryBooksCount();
    List<CategoryBooksTotalCost> getAllCategoryBooksTotalCost();
}
