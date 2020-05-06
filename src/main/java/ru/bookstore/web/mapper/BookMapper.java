package ru.bookstore.web.mapper;

import org.mapstruct.Mapper;
import ru.bookstore.business.entity.Book;
import ru.bookstore.web.dto.BookDTO;

@Mapper
public interface BookMapper extends AbstractMapper<BookDTO, Book> {

}
