package com.project.customers.entity;

import java.util.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Customer document.
 */
@Data
@Getter
@Setter
@Document(collection = "customers")
public class Customer {
  @Id
  private String id;
  private String customerType;
  private String documentType;
  @Indexed(unique = true)
  private String documentNumber;
  private String names;
  private String surnames;
  private String businessName;
  @Indexed(unique = true)
  private String email;
  private String phone;
  @CreatedDate
  private Date createdAt;
}
