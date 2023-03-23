package com.github.dudekmat.usereventstoreservice.store;

public interface UserEventRepository {

  void saveProductEvent(ProductEventData productEventData);
  void saveSearchEvent(SearchEventData searchEventData);
}
