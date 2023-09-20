package com.project.masters.repository;

import com.project.masters.entity.CustomerType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Customer type repository for data persistence.
 */
@Repository
public interface CustomerTypeRepository extends MongoRepository<CustomerType, String> {
}
