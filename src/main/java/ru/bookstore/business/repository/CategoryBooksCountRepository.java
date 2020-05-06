package ru.bookstore.business.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bookstore.business.entity.CategoryBooksCount;

@Repository
public interface CategoryBooksCountRepository extends JpaRepository<CategoryBooksCount, Long> {

}
