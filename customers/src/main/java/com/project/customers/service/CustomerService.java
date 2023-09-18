package com.project.customers.service;

import com.project.customers.entity.Customer;
import reactor.core.publisher.Mono;

/**
 * Customer service.
 */
public interface CustomerService {
  Mono<Customer> save(Mono<Customer> customer);

  Mono<Customer> findById(String id);

  Mono<Customer> findByDocumentNumber(String documentNumber);

  Mono<Customer> update(String id, Mono<Customer> customer);

  Mono<Void> delete(Customer customer);
}
