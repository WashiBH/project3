package com.project.customers.service.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.customers.events.YankiCustomerEvent;
import com.project.customers.mapper.YankiCustomerEventMapper;
import com.project.customers.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Customer kafka consumer for Yanki customer.
 */
@Slf4j
@Component
public class CustomerKafkaConsumerService {

  private final CustomerRepository customerRepository;
  private final YankiCustomerEventMapper yankiCustomerEventMapper;

  @Autowired
  public CustomerKafkaConsumerService(
      CustomerRepository customerRepository,
      YankiCustomerEventMapper yankiCustomerEventMapper
  ) {
    this.customerRepository = customerRepository;
    this.yankiCustomerEventMapper = yankiCustomerEventMapper;
  }

  /**
   * Consumer method for listener kafka topic.
   *
   * @param consumer Consumer record for get producer event.
   */
  @KafkaListener(topics = "yanki-customer-topic", groupId = "group1")
  public void consumer(ConsumerRecord<String, String> consumer) throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    YankiCustomerEvent yankiCustomer = mapper.readValue(consumer.value(), YankiCustomerEvent.class);
    customerRepository.insert(yankiCustomerEventMapper.toEntity(yankiCustomer));
  }

}
