package ru.bookstore.business.service.interfaces;

import ru.bookstore.web.dto.BookCategoryDTO;

import java.util.List;

public interface BookCategoryService {
    List<BookCategoryDTO> getCategories();
}
