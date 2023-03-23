package com.github.dudekmat.usereventstoreservice.config.serde;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.dudekmat.usereventstoreservice.event.UserSearchEvent;
import java.nio.charset.StandardCharsets;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Deserializer;

@Slf4j
public class UserSearchEventDeserializer implements Deserializer<UserSearchEvent> {

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public UserSearchEvent deserialize(String topic, byte[] data) {
    try {
      return objectMapper.readValue(new String(data, StandardCharsets.UTF_8), UserSearchEvent.class);
    } catch (Exception ex) {
      log.error("Unknown error occurred while deserializing the event");
      return null;
    }
  }
}
