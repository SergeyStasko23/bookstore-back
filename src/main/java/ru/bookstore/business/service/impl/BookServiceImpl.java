package ru.bookstore.business.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.bookstore.business.entity.Book;
import ru.bookstore.business.entity.CategoryBooksCount;
import ru.bookstore.business.entity.CategoryBooksTotalCost;
import ru.bookstore.business.repository.BookRepository;
import ru.bookstore.business.service.AbstractService;
import ru.bookstore.business.service.interfaces.BookCategoryService;
import ru.bookstore.business.service.interfaces.BookReportService;
import ru.bookstore.business.service.interfaces.BookService;
import ru.bookstore.core.report.CategoryBooksWriter;
import ru.bookstore.web.dto.AbstractFilter;
import ru.bookstore.web.dto.BookCategoryDTO;
import ru.bookstore.web.dto.BookDTO;
import ru.bookstore.web.mapper.BookMapper;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BookServiceImpl extends AbstractService implements BookService {
    private final BookRepository repository;
    private final BookMapper mapper;
    private final BookCategoryService categoryService;

    private final BookReportService bookReportService;
    private final CategoryBooksWriter writer;

    @Override
    public Long saveBook(BookDTO bookDTO) {
        Book book = mapper.toEntity(bookDTO);
        book.setCreateDate(LocalDateTime.now());
        return repository.save(book).getId();
    }

    @Override
    public Long updateBook(BookDTO bookDTO) {
        Book book = getById(bookDTO.getId());
        mapper.updateFromDTO(bookDTO, book);
        repository.save(book);
        return book.getId();
    }

    @Override
    public Long deleteBook(Long bookId) {
        repository.deleteById(bookId);
        return bookId;
    }

    @Override
    public Book getById(Long bookId) {
        return repository.getOne(bookId);
    }

    @Override
    public Page<BookDTO> getBooks(AbstractFilter filter) {
        Pageable pageable = getPageable(filter.getPage(), filter.getSize());
        return repository.findAll(pageable).map(mapper::toDTO);
    }

    @Override
    public Page<BookDTO> getBooksByCategory(String name, AbstractFilter filter) {
        Pageable pageable = getPageable(filter.getPage(), filter.getSize());
        return repository.findAllByCategoryName(name, pageable).map(mapper::toDTO);
    }

    @Override
    public List<BookCategoryDTO> getCategories() {
        return categoryService.getCategories();
    }

    @Override
    public void writeBooksToExcel(ServletOutputStream outputStream) throws IOException {
        List<CategoryBooksCount> booksCount = bookReportService.getAllCategoryBooksCount();
        List<CategoryBooksTotalCost> totalCosts = bookReportService.getAllCategoryBooksTotalCost();
        writer.build(Arrays.asList(booksCount, totalCosts), outputStream);
    }

    @Override
    public String getExcelFileName() {
        return writer.getFileName();
    }
}
