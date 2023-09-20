package com.project.masters.controller;

import com.project.masters.api.CustomerstypeApi;
import com.project.masters.dto.CustomerTypeDto;
import com.project.masters.entity.CustomerType;
import com.project.masters.mapper.CustomerTypeMapper;
import com.project.masters.service.CustomerTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
@RestController
public class CustomerTypeController implements CustomerstypeApi {

  private final CustomerTypeService customerTypeService;
  private final CustomerTypeMapper customerTypeMapper;

  @Autowired
  public CustomerTypeController(CustomerTypeService customerTypeService, CustomerTypeMapper customerTypeMapper) {
    this.customerTypeService = customerTypeService;
    this.customerTypeMapper = customerTypeMapper;
  }

  @Override
  public ResponseEntity<CustomerTypeDto> addCustomerType(CustomerTypeDto customerTypeDto) {
    CustomerType customerType =customerTypeService.save(customerTypeMapper.toEntity(customerTypeDto));
    return ResponseEntity.status(HttpStatus.CREATED).body(customerTypeMapper.toDto(customerType));
  }

  @Override
  public ResponseEntity<Void> deleteCustomerType(String id) {
    customerTypeService.delete(id);
    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
  }

  @Override
  public ResponseEntity<List<CustomerTypeDto>> getAllCustomerType() {
    return ResponseEntity.ok(customerTypeService.findAll()
      .stream()
      .map(customerTypeMapper::toDto)
      .collect(Collectors.toList()));
  }

  @Override
  public ResponseEntity<CustomerTypeDto> updateCustomerType(String id, CustomerTypeDto customerTypeDto) {
    CustomerType customerType = customerTypeMapper.toEntity(customerTypeDto);
    return ResponseEntity.ok(customerTypeMapper.toDto(customerTypeService.update(id,customerType)));
  }
}
