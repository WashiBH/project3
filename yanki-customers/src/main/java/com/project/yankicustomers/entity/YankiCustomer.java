package com.project.yankicustomers.entity;

import java.util.Date;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Yanki customer entity.
 */
@Data
@Getter
@Setter
@Document(collection = "yankicustomers")
public class YankiCustomer {
  @Id
  private String id;
  private String documentType;
  @Indexed(unique = true)
  private String documentNumber;
  private String names;
  private String surnames;
  @Indexed(unique = true)
  private String email;
  private String phone;
  @Indexed(unique = true)
  private String imei;
  @CreatedDate
  private Date createdAt;
}
