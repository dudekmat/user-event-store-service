package com.github.dudekmat.usereventstoreservice.config.serde;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.dudekmat.usereventstoreservice.event.UserProductEvent;
import java.nio.charset.StandardCharsets;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Deserializer;

@Slf4j
public class UserProductEventDeserializer implements Deserializer<UserProductEvent> {

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public UserProductEvent deserialize(String topic, byte[] data) {
    try {
      return objectMapper.readValue(new String(data, StandardCharsets.UTF_8), UserProductEvent.class);
    } catch (Exception ex) {
      log.error("Unknown error occurred while deserializing the event");
      return null;
    }
  }
}
