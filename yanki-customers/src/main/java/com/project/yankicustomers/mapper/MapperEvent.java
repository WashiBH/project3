package com.project.yankicustomers.mapper;

/**
 *
 * @param <D> Dto param.
 * @param <E> Event param.
 */
public interface MapperEvent<D, E>{
  E toEvent(D dto);
}
