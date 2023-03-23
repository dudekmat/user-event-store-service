package com.github.dudekmat.usereventstoreservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "kafka.consumer")
record KafkaConsumerProperties(String bootstrapServers, String groupId, String autoOffsetReset,
                               Integer maxPollRecords) {

}
