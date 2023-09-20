package com.project.yankicustomers.service;

import com.project.yankicustomers.entity.YankiCustomer;
import reactor.core.publisher.Mono;

/**
 * Yanki customer service.
 */
public interface YankiCustomerService {
  Mono<YankiCustomer> save(Mono<YankiCustomer> yankiCustomer);

  Mono<YankiCustomer> findById(String id);

  Mono<YankiCustomer> findByDocumentNumber(String documentNumber);

  Mono<YankiCustomer> update(String id, Mono<YankiCustomer> yankiCustomer);

  Mono<Void> delete(YankiCustomer yankiCustomer);
}
