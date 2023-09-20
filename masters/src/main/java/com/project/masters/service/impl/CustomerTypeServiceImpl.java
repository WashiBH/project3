package com.project.masters.service.impl;

import com.project.masters.entity.CustomerType;
import com.project.masters.repository.CustomerTypeRepository;
import com.project.masters.service.CustomerTypeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Customer type service implements.
 */
@Service
public class CustomerTypeServiceImpl implements CustomerTypeService {

  private final CustomerTypeRepository customerTypeRepository;

  @Autowired
  public CustomerTypeServiceImpl(CustomerTypeRepository customerTypeRepository) {
    this.customerTypeRepository = customerTypeRepository;
  }

  @Override
  public CustomerType save(CustomerType customerType) {
    return customerTypeRepository.insert(customerType);
  }

  @Cacheable(value = "customerTypeCache")
  @Override
  public List<CustomerType> findAll() {
    return customerTypeRepository.findAll();
  }

  @Override
  public CustomerType update(String id, CustomerType customerType) {
    if (customerTypeRepository.existsById(id)) {
      customerType.setId(id);
    }
    return customerTypeRepository.save(customerType);
  }

  @Override
  public void delete(String id) {
    customerTypeRepository.deleteById(id);
  }
}
