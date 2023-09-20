package com.project.yankicustomers.controller;

import com.project.yankicustomers.api.YankicustomersApi;
import com.project.yankicustomers.dto.YankiCustomerDto;
import com.project.yankicustomers.mapper.YankiCustomerEventMapper;
import com.project.yankicustomers.mapper.YankiCustomerMapper;
import com.project.yankicustomers.service.YankiCustomerService;
import com.project.yankicustomers.service.kafka.YankiCustomerKafkaPublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * Yanki customer rest controller.
 */
@RestController
public class YankiCustomerController implements YankicustomersApi {

  private final YankiCustomerService yankiCustomerService;
  private final YankiCustomerMapper yankiCustomerMapper;
  private final YankiCustomerEventMapper yankiCustomerEventMapper;
  private final YankiCustomerKafkaPublisherService yankiCustomerKafkaPublisherService;

  @Autowired
  public YankiCustomerController(
    YankiCustomerService yankiCustomerService,
    YankiCustomerMapper yankiCustomerMapper,
    YankiCustomerKafkaPublisherService yankiCustomerKafkaPublisherService,
    YankiCustomerEventMapper yankiCustomerEventMapper
  ) {
    this.yankiCustomerService = yankiCustomerService;
    this.yankiCustomerMapper = yankiCustomerMapper;
    this.yankiCustomerKafkaPublisherService = yankiCustomerKafkaPublisherService;
    this.yankiCustomerEventMapper = yankiCustomerEventMapper;
  }

  @Override
  public Mono<ResponseEntity<YankiCustomerDto>> addYankiCustomer(Mono<YankiCustomerDto> yankiCustomerDto, ServerWebExchange exchange) {
    return yankiCustomerService.save(yankiCustomerDto.map(yankiCustomerMapper::toEntity))
      .map(yankiCustomerMapper::toDto)
      .map(yanki -> {
        this.yankiCustomerKafkaPublisherService.publish(yankiCustomerEventMapper.toEvent(yanki));
        return ResponseEntity.status(HttpStatus.CREATED).body(yanki);
      });
  }

  @Override
  public Mono<ResponseEntity<Void>> deleteYankiCustomer(String id, ServerWebExchange exchange) {
    return yankiCustomerService.findById(id)
      .flatMap(yanki -> yankiCustomerService.delete(yanki)
        .then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT))))
      .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @Override
  public Mono<ResponseEntity<YankiCustomerDto>> getYankiCustomerByDocumentNumber(String document, ServerWebExchange exchange) {
    return yankiCustomerService.findByDocumentNumber(document)
      .map(yankiCustomerMapper::toDto)
      .map(ResponseEntity::ok)
      .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @Override
  public Mono<ResponseEntity<YankiCustomerDto>> getYankiCustomerById(String id, ServerWebExchange exchange) {
    return yankiCustomerService.findById(id)
      .map(yankiCustomerMapper::toDto)
      .map(ResponseEntity::ok)
      .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @Override
  public Mono<ResponseEntity<YankiCustomerDto>> updateYankiCustomer(String id, Mono<YankiCustomerDto> yankiCustomerDto, ServerWebExchange exchange) {
    return yankiCustomerService.update(id, yankiCustomerDto.map(yankiCustomerMapper::toEntity))
      .map(yankiCustomerMapper::toDto)
      .map(ResponseEntity::ok)
      .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }
}
