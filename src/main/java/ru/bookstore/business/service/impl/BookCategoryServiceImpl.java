package ru.bookstore.business.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bookstore.business.repository.BookCategoryRepository;
import ru.bookstore.business.service.interfaces.BookCategoryService;
import ru.bookstore.web.dto.BookCategoryDTO;
import ru.bookstore.web.mapper.BookCategoryMapper;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookCategoryServiceImpl implements BookCategoryService {
    private final BookCategoryRepository repository;
    private final BookCategoryMapper mapper;

    @Override
    public List<BookCategoryDTO> getCategories() {
        return mapper.toDTOs(repository.findAll());
    }
}
