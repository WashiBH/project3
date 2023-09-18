package com.project.customers.service.impl;

import com.project.customers.entity.Customer;
import com.project.customers.repository.CustomerRepository;
import com.project.customers.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * Customer service implements.
 */
@Service
public class CustomerServiceImpl implements CustomerService {

  private final CustomerRepository customerRepository;

  @Autowired
  public CustomerServiceImpl(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  @Override
  public Mono<Customer> save(Mono<Customer> customer) {
    return customer.flatMap(customerRepository::insert);
  }

  @Override
  public Mono<Customer> findById(String id) {
    return customerRepository.findById(id);
  }

  @Override
  public Mono<Customer> findByDocumentNumber(String documentNumber) {
    return customerRepository.findByDocumentNumber(documentNumber);
  }

  @Override
  public Mono<Customer> update(String id, Mono<Customer> customer) {
    return customerRepository.findById(id)
      .flatMap(c -> customer)
      .doOnNext(e -> e.setId(id))
      .flatMap(customerRepository::save);
  }

  @Override
  public Mono<Void> delete(Customer customer) {
    return customerRepository.delete(customer);
  }
}
