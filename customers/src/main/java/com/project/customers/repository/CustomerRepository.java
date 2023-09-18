package com.project.customers.repository;

import com.project.customers.entity.Customer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * Customer reactive repository.
 */
@Repository
public interface CustomerRepository  extends ReactiveMongoRepository<Customer, String> {
  Mono<Customer> findByDocumentNumber(String documentNumber);
}
