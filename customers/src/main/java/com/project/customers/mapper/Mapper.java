package com.project.customers.mapper;

/**
 * Mapper interface.
 *
 * @param <D> DTO param.
 * @param <E> Entity param.
 */
public interface Mapper<D, E> {
  E toEntity(D dto);

  D toDto(E entity);
}
