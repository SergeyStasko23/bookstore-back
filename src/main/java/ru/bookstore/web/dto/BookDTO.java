package ru.bookstore.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class BookDTO extends AbstractEntityDTO {
    String name;
    String description;
    BigDecimal price;
    Boolean isActive;
    Integer number;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime createDate;
}
