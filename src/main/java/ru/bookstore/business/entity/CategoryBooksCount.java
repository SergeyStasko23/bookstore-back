package ru.bookstore.business.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "V_CATEGORY_BOOKS_COUNT")
public class CategoryBooksCount extends CategoryBooksReport {
    @Id
    private Long id;
    private Long booksCount;
}
