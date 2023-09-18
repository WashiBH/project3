package com.project.customers.mapper;

import com.project.customers.dto.CustomerDto;
import com.project.customers.entity.Customer;
import java.time.ZoneOffset;
import java.util.Date;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * Customer mapper.
 */
@Component
public class CustomerMapper implements Mapper<CustomerDto, Customer> {
  @Override
  public Customer toEntity(CustomerDto dto) {
    Customer customer = new Customer();
    BeanUtils.copyProperties(dto, customer);
    customer.setCustomerType(dto.getCustomerType().getValue());
    customer.setDocumentType(dto.getDocumentType().getValue());
    customer.setCreatedAt(new Date());
    return customer;
  }

  @Override
  public CustomerDto toDto(Customer entity) {
    CustomerDto customerDto = new CustomerDto(null, null, null, null, null, null, null);
    BeanUtils.copyProperties(entity, customerDto);
    customerDto.setCustomerType(CustomerDto.CustomerTypeEnum.valueOf(entity.getCustomerType()));
    customerDto.setDocumentType(CustomerDto.DocumentTypeEnum.valueOf(entity.getDocumentType()));
    customerDto.setCreatedAt(entity.getCreatedAt().toInstant().atOffset(ZoneOffset.UTC));
    return customerDto;
  }
}
