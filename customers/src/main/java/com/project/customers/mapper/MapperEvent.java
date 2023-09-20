package com.project.customers.mapper;

/**
 * Event mapper for kafka consumer data.
 *
 * @param <E> Event.
 * @param <R> Response.
 */
public interface MapperEvent<E, R> {
  R toEntity(E event);
}
