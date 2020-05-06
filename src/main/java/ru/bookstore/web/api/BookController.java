package ru.bookstore.web.api;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.bookstore.business.service.interfaces.BookService;
import ru.bookstore.web.dto.AbstractFilter;
import ru.bookstore.web.dto.BookCategoryDTO;
import ru.bookstore.web.dto.BookDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static ru.bookstore.web.api.Api.API;
import static ru.bookstore.web.util.ControllerUtil.XLSX_MEDIA_TYPE;
import static ru.bookstore.web.util.ControllerUtil.setResponseHeadersForAttachmentFile;

@RequiredArgsConstructor
@RequestMapping(API)
@RestController
public class BookController {
    private final BookService bookService;

    @PostMapping("/books")
    public Long saveBook(@RequestBody BookDTO bookDTO) {
        return bookService.saveBook(bookDTO);
    }

    @PutMapping("/books")
    public Long updateBook(@RequestBody BookDTO bookDTO) {
        return bookService.updateBook(bookDTO);
    }

    @DeleteMapping("/books/{bookId}")
    public Long deleteBook(@PathVariable Long bookId) {
        return bookService.deleteBook(bookId);
    }

    @GetMapping("/books")
    public Page<BookDTO> getBooks(AbstractFilter filter) {
        return bookService.getBooks(filter);
    }

    @GetMapping("/categories/{name}/books")
    public Page<BookDTO> getBooksByCategory(@PathVariable String name, AbstractFilter filter) {
        return bookService.getBooksByCategory(name, filter);
    }

    @GetMapping("/categories")
    public List<BookCategoryDTO> getCategories() {
        return bookService.getCategories();
    }

    @PostMapping("/books/excel")
    public void getBooksExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        setResponseHeadersForAttachmentFile(request, response, bookService.getExcelFileName(), XLSX_MEDIA_TYPE);
        bookService.writeBooksToExcel(response.getOutputStream());
    }
}
