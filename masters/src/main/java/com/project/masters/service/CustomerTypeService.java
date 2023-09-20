package com.project.masters.service;

import com.project.masters.entity.CustomerType;
import reactor.core.publisher.Mono;

import java.util.List;
public interface CustomerTypeService {

  CustomerType save(CustomerType customerType);

  List<CustomerType> findAll();

  CustomerType update(String id, CustomerType customerType);

  void delete(String id);
}
