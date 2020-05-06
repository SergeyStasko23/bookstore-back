package ru.bookstore.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AbstractFilter<T> {
    private Integer page;
    private Integer size;
    private T filter;
}
