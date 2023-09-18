package com.project.customers.controller;

import com.project.customers.api.CustomersApi;
import com.project.customers.dto.CustomerDto;
import com.project.customers.mapper.CustomerMapper;
import com.project.customers.service.CustomerService;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Customer rest controller.
 */
@RestController
public class CustomerController implements CustomersApi {

  private static final String TIMESTAMP = "timestamp";

  private final CustomerService customerService;

  private final CustomerMapper customerMapper;

  @Autowired
  public CustomerController(CustomerService customerService, CustomerMapper customerMapper) {
    this.customerService = customerService;
    this.customerMapper = customerMapper;
  }

  @Override
  public Mono<ResponseEntity<Map<String, Object>>> addCustomer(
      Mono<CustomerDto> customerDto,
      ServerWebExchange exchange
  ) {
    Map<String, Object> response = new HashMap<>();
    return customerService.save(customerDto.map(customerMapper::toEntity))
      .map(customerMapper::toDto)
      .map(customer -> {
        response.put("customer", customer);
        response.put("message", "Cliente registrado correctamente");
        response.put(TIMESTAMP, new Date());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
      })
      .onErrorResume(WebExchangeBindException.class, getPatternError(response))
      .onErrorResume(DuplicateKeyException.class, getDuplicateError(response));
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
      .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @Override
  public Mono<ResponseEntity<CustomerDto>> getCustomerById(String id, ServerWebExchange exchange) {
    return customerService.findById(id)
      .map(customerMapper::toDto)
      .map(ResponseEntity::ok)
      .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @Override
  public Mono<ResponseEntity<Map<String, Object>>> updateCustomer(
      String id, Mono<CustomerDto> customerDto,
      ServerWebExchange exchange
  ) {
    Map<String, Object> response = new HashMap<>();
    return customerService.update(id, customerDto.map(customerMapper::toEntity))
      .map(customerMapper::toDto)
      .map(c -> {
        response.put("customer", c);
        response.put("message", "Cliente guardado con éxito");
        response.put(TIMESTAMP, new Date());
        return ResponseEntity.status(HttpStatus.OK).body(response);
      })
      .onErrorResume(WebExchangeBindException.class, getPatternError(response))
      .onErrorResume(DuplicateKeyException.class, getDuplicateError(response))
      .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  private static Function<Throwable, Mono<ResponseEntity<Map<String, Object>>>> getPatternError(
      Map<String, Object> response
  ) {
    return t -> Mono.just(t).cast(WebExchangeBindException.class)
      .flatMap(e -> Mono.just(e.getFieldErrors()))
      .flatMapMany(Flux::fromIterable)
      .map(fieldError -> "Campo " + fieldError.getField() + " " + fieldError.getDefaultMessage())
      .collectList()
      .flatMap(l -> {
        response.put(TIMESTAMP, new Date());
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("errors", l);
        return Mono.just(ResponseEntity.badRequest().body(response));
      });
  }

  private static Function<Throwable, Mono<ResponseEntity<Map<String, Object>>>> getDuplicateError(
      Map<String, Object> response
  ) {
    return t -> Mono.just(t).cast(DuplicateKeyException.class)
      .flatMap(l -> {
        response.put(TIMESTAMP, new Date());
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("errors", l.getMessage());
        return Mono.just(ResponseEntity.badRequest().body(response));
      });
  }
}
