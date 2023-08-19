package com.github.dudekmat.usereventstoreservice.event;

import static com.github.dudekmat.usereventstoreservice.config.kafka.KafkaConsumerConfig.USER_PRODUCT_EVENT_TOPIC;
import static com.github.dudekmat.usereventstoreservice.config.kafka.KafkaConsumerConfig.USER_SEARCH_EVENT_TOPIC;

import com.github.dudekmat.usereventstoreservice.store.ProductEventData;
import com.github.dudekmat.usereventstoreservice.store.SearchEventData;
import com.github.dudekmat.usereventstoreservice.store.UserEventRepository;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
class UserEventListener {

  private final UserEventRepository userEventRepository;

  @KafkaListener(
      topics = USER_SEARCH_EVENT_TOPIC,
      clientIdPrefix = USER_SEARCH_EVENT_TOPIC,
      autoStartup = "true",
      errorHandler = "eventListenerErrorHandler"
  )
  void handleUserSearchEvent(@Payload @Valid UserSearchEvent userSearchEvent) {
    log.info("Processing user search event: {}", userSearchEvent);
    userEventRepository.saveSearchEvent(SearchEventData.builder()
        .id(UUID.randomUUID())
        .eventType(userSearchEvent.eventType())
        .userId(userSearchEvent.userId())
        .sessionId(userSearchEvent.sessionId())
        .eventTime(userSearchEvent.eventTime())
        .productId(userSearchEvent.productId())
        .query(userSearchEvent.query())
        .searchParams(userSearchEvent.searchParams())
        .platform(userSearchEvent.platform())
        .build());
  }

  @KafkaListener(
      topics = USER_PRODUCT_EVENT_TOPIC,
      clientIdPrefix = USER_PRODUCT_EVENT_TOPIC,
      autoStartup = "true",
      errorHandler = "eventListenerErrorHandler"
  )
  void handleUserProductEvent(@Payload @Valid UserProductEvent userProductEvent) {
    log.info("Processing user product event: {}", userProductEvent);
    userEventRepository.saveProductEvent(ProductEventData.builder()
        .id(UUID.randomUUID())
        .eventType(userProductEvent.eventType())
        .userId(userProductEvent.userId())
        .sessionId(userProductEvent.sessionId())
        .eventTime(userProductEvent.eventTime())
        .productId(userProductEvent.productId())
        .pageName(userProductEvent.pageName())
        .boxName(userProductEvent.boxName())
        .boxPosition(userProductEvent.boxPosition())
        .platform(userProductEvent.platform())
        .build());
  }
}
