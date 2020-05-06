package ru.bookstore.web.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

public interface AbstractMapper<D, E> {
    D toDTO(E entity);

    List<D> toDTOs(List<E> entities);

    E toEntity(D dto);

    List<E> toEntities(List<D> dtos);

    @Mapping(ignore = true, target = "id")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDTO(D dto, @MappingTarget E entity);
}
