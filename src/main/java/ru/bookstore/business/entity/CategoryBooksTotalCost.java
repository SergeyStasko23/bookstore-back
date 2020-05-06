package ru.bookstore.business.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "V_CATEGORY_BOOKS_TOTAL_COST")
public class CategoryBooksTotalCost extends CategoryBooksReport {
    @Id
    private Long id;
    private BigDecimal totalCost;
}
