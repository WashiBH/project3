package com.project.masters.mapper;

import com.project.masters.dto.CustomerTypeDto;
import com.project.masters.entity.CustomerType;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * Customer type mapper.
 */
@Component
public class CustomerTypeMapper implements Mapper<CustomerTypeDto, CustomerType> {
  @Override
  public CustomerType toEntity(CustomerTypeDto dto) {
    CustomerType customerType = new CustomerType();
    BeanUtils.copyProperties(dto, customerType);
    return customerType;
  }

  @Override
  public CustomerTypeDto toDto(CustomerType entity) {
    CustomerTypeDto customerTypeDto = new CustomerTypeDto(null);
    BeanUtils.copyProperties(entity, customerTypeDto);
    return customerTypeDto;
  }

}
