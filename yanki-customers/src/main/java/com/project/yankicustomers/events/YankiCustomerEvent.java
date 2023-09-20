package com.project.yankicustomers.events;

import lombok.Data;

@Data
public class YankiCustomerEvent {
  private String documentType;
  private String documentNumber;
  private String names;
  private String surnames;
  private String email;
  private String phone;
}
