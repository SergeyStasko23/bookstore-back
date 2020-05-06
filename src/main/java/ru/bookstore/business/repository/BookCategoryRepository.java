package ru.bookstore.business.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bookstore.business.entity.BookCategory;

@Repository
public interface BookCategoryRepository extends JpaRepository<BookCategory, Long> {

}
