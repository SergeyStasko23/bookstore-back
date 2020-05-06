package ru.bookstore.business.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public abstract class AbstractService {
    protected Pageable getPageable(Integer page, Integer size) {
        if (page == null) {
            page = 0;
        }
        if (size == null) {
            size = 20;
        }
        return PageRequest.of(page, size);
    }

    protected Pageable getPageable(Integer page, Integer size, Sort sort) {
        if (page == null) {
            page = 0;
        }
        if (size == null) {
            size = 20;
        }
        return PageRequest.of(page, size, sort);
    }
}
