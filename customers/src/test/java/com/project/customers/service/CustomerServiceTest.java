package com.project.customers.service;

import com.project.customers.entity.Customer;
import com.project.customers.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

  @InjectMocks
  private CustomerServiceImpl customerService;

  @Test
  @DisplayName("Test for find by Id from customer")
  public void testFindById(){

    Customer expected = new Customer();

    Mono<Customer> actual = customerService.findById("jkjj");

    StepVerifier.create(actual)
      .expectNext(expected)
      .expectComplete()
      .verify();
  }

}
