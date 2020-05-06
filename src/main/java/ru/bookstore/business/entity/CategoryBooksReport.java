package ru.bookstore.business.entity;

import lombok.Data;

import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public abstract class CategoryBooksReport {
    private String categoryName;
}
