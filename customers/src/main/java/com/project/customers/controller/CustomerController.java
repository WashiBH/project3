package com.project.customers.controller;

import com.project.customers.api.CustomersApi;
import com.project.customers.dto.CustomerDto;
import com.project.customers.mapper.CustomerMapper;
import com.project.customers.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * Customer rest controller.
 */
@RestController
public class CustomerController implements CustomersApi {

  private final CustomerService customerService;

  private final CustomerMapper customerMapper;

  @Autowired
  public CustomerController(CustomerService customerService, CustomerMapper customerMapper) {
    this.customerService = customerService;
    this.customerMapper = customerMapper;
  }

  @Override
  public Mono<ResponseEntity<CustomerDto>> addCustomer(
      Mono<CustomerDto> customerDto,
      ServerWebExchange exchange
  ) {
    return customerService.save(customerDto.map(customerMapper::toEntity))
      .map(customerMapper::toDto)
      .map(customer -> ResponseEntity.status(HttpStatus.CREATED).body(customer));
  }

  @Override
  public Mono<ResponseEntity<Void>> deleteCustomer(String id, ServerWebExchange exchange) {
    return customerService.findById(id)
      .flatMap(customer -> customerService.delete(customer)
        .then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT))))
      .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @Override
  public Mono<ResponseEntity<CustomerDto>> getCustomerByDocumentNumber(
      String document,
      ServerWebExchange exchange
  ) {
    return customerService.findByDocumentNumber(document)
      .map(customerMapper::toDto)
      .map(ResponseEntity::ok)
      .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @Override
  public Mono<ResponseEntity<CustomerDto>> getCustomerById(String id, ServerWebExchange exchange) {
    return customerService.findById(id)
      .map(customerMapper::toDto)
      .map(ResponseEntity::ok)
      .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @Override
  public Mono<ResponseEntity<CustomerDto>> updateCustomer(
      String id, Mono<CustomerDto> customerDto,
      ServerWebExchange exchange
  ) {
    return customerService.update(id, customerDto.map(customerMapper::toEntity))
      .map(customerMapper::toDto)
      .map(ResponseEntity::ok)
      .defaultIfEmpty(ResponseEntity.notFound().build());
  }

}
