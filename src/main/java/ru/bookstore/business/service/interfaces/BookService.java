package ru.bookstore.business.service.interfaces;

import org.springframework.data.domain.Page;
import ru.bookstore.business.entity.Book;
import ru.bookstore.web.dto.AbstractFilter;
import ru.bookstore.web.dto.BookCategoryDTO;
import ru.bookstore.web.dto.BookDTO;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.util.List;

public interface BookService {
    Long saveBook(BookDTO bookDTO);

    Long updateBook(BookDTO bookDTO);

    Long deleteBook(Long bookId);

    Book getById(Long bookId);

    Page<BookDTO> getBooks(AbstractFilter filter);

    Page<BookDTO> getBooksByCategory(String name, AbstractFilter filter);

    List<BookCategoryDTO> getCategories();

    void writeBooksToExcel(ServletOutputStream outputStream) throws IOException;

    String getExcelFileName();
}
