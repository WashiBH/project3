package com.project.yankicustomers.service.impl;

import com.project.yankicustomers.entity.YankiCustomer;
import com.project.yankicustomers.repository.YankiCustomerRepository;
import com.project.yankicustomers.service.YankiCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * Yanki customer service implements.
 */
@Service
public class YankiCustomerServiceImpl implements YankiCustomerService {

  private final YankiCustomerRepository yankiCustomerRepository;

  @Autowired
  public YankiCustomerServiceImpl(YankiCustomerRepository yankiCustomerRepository) {
    this.yankiCustomerRepository = yankiCustomerRepository;
  }

  @Override
  public Mono<YankiCustomer> save(Mono<YankiCustomer> yankiCustomer) {
    return yankiCustomer.flatMap(yankiCustomerRepository::insert);
  }

  @Override
  public Mono<YankiCustomer> findById(String id) {
    return yankiCustomerRepository.findById(id);
  }

  @Override
  public Mono<YankiCustomer> findByDocumentNumber(String documentNumber) {
    return yankiCustomerRepository.findByDocumentNumber(documentNumber);
  }

  @Override
  public Mono<YankiCustomer> update(String id, Mono<YankiCustomer> yankiCustomer) {
    return yankiCustomerRepository.findById(id)
      .doOnNext(yanki -> yanki.setId(id))
      .flatMap(yankiCustomerRepository::save);
  }

  @Override
  public Mono<Void> delete(YankiCustomer yankiCustomer) {
    return yankiCustomerRepository.delete(yankiCustomer);
  }
}
