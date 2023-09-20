package com.project.yankicustomers.mapper;

import com.project.yankicustomers.dto.YankiCustomerDto;
import com.project.yankicustomers.events.YankiCustomerEvent;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * Yanki customer event for kafka producer.
 */
@Component
public class YankiCustomerEventMapper implements MapperEvent<YankiCustomerDto, YankiCustomerEvent> {

  @Override
  public YankiCustomerEvent toEvent(YankiCustomerDto dto) {
    YankiCustomerEvent yankiCustomerEvent = new YankiCustomerEvent();
    BeanUtils.copyProperties(dto, yankiCustomerEvent);
    yankiCustomerEvent.setDocumentType(dto.getDocumentType().getValue());
    return yankiCustomerEvent;
  }
}
