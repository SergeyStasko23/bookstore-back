package ru.bookstore.business.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "BOOK")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Boolean isActive;
    private Integer number;
    private LocalDateTime createDate;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private BookCategory category;
}
