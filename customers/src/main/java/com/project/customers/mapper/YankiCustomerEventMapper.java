package com.project.customers.mapper;

import com.project.customers.entity.Customer;
import com.project.customers.events.YankiCustomerEvent;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Date;
/**
 * Yanki customer event for kafka producer.
 */
@Component
public class YankiCustomerEventMapper implements MapperEvent<YankiCustomerEvent, Customer> {

  private static final String PERSONA = "PERSONA";

  @Override
  public Customer toEntity(YankiCustomerEvent event) {
    Customer customer = new Customer();
    BeanUtils.copyProperties(event, customer);
    customer.setCustomerType(PERSONA);
    customer.setBusinessName(event.getNames().concat(" ").concat(event.getSurnames()));
    customer.setCreatedAt(new Date());
    return customer;
  }
}
