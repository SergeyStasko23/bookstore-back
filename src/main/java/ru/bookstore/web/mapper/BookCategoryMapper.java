package ru.bookstore.web.mapper;

import org.mapstruct.Mapper;
import ru.bookstore.business.entity.BookCategory;
import ru.bookstore.web.dto.BookCategoryDTO;

@Mapper
public interface BookCategoryMapper extends AbstractMapper<BookCategoryDTO, BookCategory> {

}
