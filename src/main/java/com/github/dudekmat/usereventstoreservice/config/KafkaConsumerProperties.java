package com.github.dudekmat.usereventstoreservice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "kafka.consumer")
@Data
class KafkaConsumerProperties {

  private String bootstrapServers;
  private String groupId;
  private String autoOffsetReset;
  private Integer maxPollRecords;
}
