package com.github.dudekmat.usereventstoreservice.store;

import static com.github.dudekmat.usereventstoreservice.config.elasticsearch.ElasticsearchConfig.PRODUCT_EVENT_INDEX;
import static com.github.dudekmat.usereventstoreservice.config.elasticsearch.ElasticsearchConfig.SEARCH_EVENT_INDEX;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
class UserEventElasticsearchRepository implements UserEventRepository {

  private final ElasticsearchClient elasticsearchClient;

  private static final String ERROR_MESSAGE = "Error occurred indexing document: {}, message: {}";

  @Override
  public void saveEvent(EventData eventData) {
    try {
      elasticsearchClient.index(request ->
          request
              .index(resolveIndex(eventData))
              .id(eventData.id())
              .document(eventData));
    } catch (IOException ex) {
      log.error(ERROR_MESSAGE, eventData, ex.getMessage());
    }
  }

  private String resolveIndex(EventData eventData) {
    if (eventData instanceof ProductEventData) {
      return PRODUCT_EVENT_INDEX;
    } else if (eventData instanceof SearchEventData) {
      return SEARCH_EVENT_INDEX;
    } else {
      throw new RuntimeException("Unknown index");
    }
  }
}
