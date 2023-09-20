package com.project.customers.events;

import com.project.customers.dto.CustomerDto.DocumentTypeEnum;
import lombok.Data;

import java.util.Date;

/**
 * Yanki customer for kafka consumer.
 */
@Data
public class YankiCustomerEvent {
  private String documentType;
  private String documentNumber;
  private String names;
  private String surnames;
  private String email;
  private String phone;
}
