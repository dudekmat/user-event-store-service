package com.github.dudekmat.usereventstoreservice.store;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ProductEventData implements EventData {

  String id;
  String eventType;
  String userId;
  String sessionId;
  Long eventTime;
  String productId;
  String pageName;
  String boxName;
  String boxPosition;
  String platform;

  @Override
  public String id() {
    return id;
  }
}
