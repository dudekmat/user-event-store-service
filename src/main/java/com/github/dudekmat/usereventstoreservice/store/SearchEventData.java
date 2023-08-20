package com.github.dudekmat.usereventstoreservice.store;

import java.util.UUID;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class SearchEventData implements EventData {

  String id;
  String eventType;
  String userId;
  String sessionId;
  Long eventTime;
  String productId;
  String query;
  String searchParams;
  String platform;

  @Override
  public String id() {
    return id;
  }
}
