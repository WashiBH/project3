package com.project.yankicustomers.repository;

import com.project.yankicustomers.entity.YankiCustomer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * Yanki customer reactive repository.
 */
@Repository
public interface YankiCustomerRepository extends ReactiveMongoRepository<YankiCustomer, String> {
  Mono<YankiCustomer> findByDocumentNumber(String documentNumber);
}
