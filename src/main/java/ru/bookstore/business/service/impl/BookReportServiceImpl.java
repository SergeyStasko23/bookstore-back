package ru.bookstore.business.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bookstore.business.entity.CategoryBooksCount;
import ru.bookstore.business.entity.CategoryBooksTotalCost;
import ru.bookstore.business.repository.CategoryBooksCountRepository;
import ru.bookstore.business.repository.CategoryBooksTotalCostRepository;
import ru.bookstore.business.service.interfaces.BookReportService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookReportServiceImpl implements BookReportService {
    private final CategoryBooksTotalCostRepository totalCostRepository;
    private final CategoryBooksCountRepository countRepository;

    @Override
    public List<CategoryBooksCount> getAllCategoryBooksCount() {
        return countRepository.findAll();
    }

    @Override
    public List<CategoryBooksTotalCost> getAllCategoryBooksTotalCost() {
        return totalCostRepository.findAll();
    }
}
