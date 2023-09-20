package com.project.yankicustomers.mapper;


import com.project.yankicustomers.dto.YankiCustomerDto;
import com.project.yankicustomers.entity.YankiCustomer;
import java.time.ZoneOffset;
import java.util.Date;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * Yanki customer mapper.
 */
@Component
public class YankiCustomerMapper implements Mapper<YankiCustomerDto, YankiCustomer> {
  @Override
  public YankiCustomer toEntity(YankiCustomerDto dto) {
    YankiCustomer yankiCustomer = new YankiCustomer();
    BeanUtils.copyProperties(dto, yankiCustomer);
    yankiCustomer.setDocumentType(dto.getDocumentType().getValue());
    yankiCustomer.setCreatedAt(new Date());
    return yankiCustomer;
  }

  @Override
  public YankiCustomerDto toDto(YankiCustomer entity) {
    YankiCustomerDto yankiCustomerDto = new YankiCustomerDto(null,
        null, null, null, null, null, null);
    BeanUtils.copyProperties(entity, yankiCustomerDto);
    String type = entity.getDocumentType();
    yankiCustomerDto.setDocumentType(YankiCustomerDto.DocumentTypeEnum.valueOf(type));
    yankiCustomerDto.setCreatedAt(entity.getCreatedAt().toInstant().atOffset(ZoneOffset.UTC));
    return yankiCustomerDto;
  }

}
