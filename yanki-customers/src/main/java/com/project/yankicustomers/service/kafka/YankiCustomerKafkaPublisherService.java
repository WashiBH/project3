package com.project.yankicustomers.service.kafka;

import com.project.yankicustomers.dto.YankiCustomerDto;
import com.project.yankicustomers.events.YankiCustomerEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * Yanki customer producer.
 */
@Component
public class YankiCustomerKafkaPublisherService {
  @Autowired
  private KafkaTemplate<String, YankiCustomerEvent> producer;

  public void publish(YankiCustomerEvent yankiCustomer) {
    this.producer.send("yanki-customer-topic", yankiCustomer);
  }
}
