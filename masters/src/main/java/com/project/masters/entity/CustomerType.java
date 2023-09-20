package com.project.masters.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * Customer type entity.
 */
@Getter
@Setter
@NoArgsConstructor
@Document(collection = "customerstype")
public class CustomerType implements Serializable {
  @Id
  private String id;
  private String type;
}
