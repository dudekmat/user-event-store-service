package com.github.dudekmat.usereventstoreservice.event;

import static com.github.dudekmat.usereventstoreservice.config.kafka.KafkaConsumerConfig.USER_PRODUCT_EVENT_TOPIC;
import static com.github.dudekmat.usereventstoreservice.config.kafka.KafkaConsumerConfig.USER_SEARCH_EVENT_TOPIC;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
class UserEventListener {

  @KafkaListener(
      topics = USER_SEARCH_EVENT_TOPIC,
      clientIdPrefix = USER_SEARCH_EVENT_TOPIC,
      autoStartup = "true",
      errorHandler = "eventListenerErrorHandler"
  )
  void handleUserSearchEvent(@Payload @Valid UserSearchEvent userSearchEvent) {
    log.info("Processing user search event: {}", userSearchEvent);
  }

  @KafkaListener(
      topics = USER_PRODUCT_EVENT_TOPIC,
      clientIdPrefix = USER_PRODUCT_EVENT_TOPIC,
      autoStartup = "true",
      errorHandler = "eventListenerErrorHandler"
  )
  void handleUserProductEvent(@Payload @Valid UserProductEvent userProductEvent) {
    log.info("Processing user product event: {}", userProductEvent);
  }
}
