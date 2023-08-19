package com.github.dudekmat.usereventstoreservice.store;

import static com.github.dudekmat.usereventstoreservice.config.elasticsearch.ElasticsearchConfig.PRODUCT_EVENT_INDEX;

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

  private static final String ERROR_MESSAGE = "Error occurred indexing document: ";

  @Override
  public void saveProductEvent(ProductEventData productEventData) {
    try {
      elasticsearchClient.index(request ->
          request
              .index(PRODUCT_EVENT_INDEX)
              .id(productEventData.getProductId())
              .document(productEventData));
    } catch (IOException ex) {
      log.error(ERROR_MESSAGE + productEventData);
    }
  }

  @Override
  public void saveSearchEvent(SearchEventData searchEventData) {
    try {
      elasticsearchClient.index(request ->
          request
              .index(PRODUCT_EVENT_INDEX)
              .id(searchEventData.getProductId())
              .document(searchEventData));
    } catch (IOException ex) {
      log.error(ERROR_MESSAGE + searchEventData);
    }
  }
}
