package ru.bookstore.business.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bookstore.business.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Page<Book> findAllByCategoryName(String name, Pageable pageable);
}
