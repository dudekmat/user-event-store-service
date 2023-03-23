package com.github.dudekmat.usereventstoreservice.config.kafka;

import com.github.dudekmat.usereventstoreservice.config.serde.UserProductEventDeserializer;
import com.github.dudekmat.usereventstoreservice.config.serde.UserSearchEventDeserializer;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.DelegatingByTopicDeserializer;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@Configuration
@EnableConfigurationProperties(KafkaConsumerProperties.class)
@RequiredArgsConstructor
public class KafkaConsumerConfig {

  private final KafkaConsumerProperties kafkaConsumerProperties;

  public static final String USER_SEARCH_EVENT_TOPIC = "user-search-event";
  public static final String USER_PRODUCT_EVENT_TOPIC = "user-product-event";

  @Bean
  public Map<String, Object> consumerConfigs() {
    Map<String, Object> props = new HashMap<>();
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaConsumerProperties.bootstrapServers());
    props.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaConsumerProperties.groupId());
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
    props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, kafkaConsumerProperties.maxPollRecords());
    props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, kafkaConsumerProperties.autoOffsetReset());
    props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
    props.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS,
        DelegatingByTopicDeserializer.class);
    props.put(DelegatingByTopicDeserializer.VALUE_SERIALIZATION_TOPIC_CONFIG,
        USER_SEARCH_EVENT_TOPIC + ":" + UserSearchEventDeserializer.class.getName() + "," +
            USER_PRODUCT_EVENT_TOPIC + ":" + UserProductEventDeserializer.class.getName());
    return props;
  }

  @Bean
  public ConsumerFactory<String, Object> consumerFactory() {
    return new DefaultKafkaConsumerFactory<>(consumerConfigs());
  }

  @Bean
  public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Object>> kafkaListenerContainerFactory() {
    var factory = new ConcurrentKafkaListenerContainerFactory<String, Object>();
    factory.setConsumerFactory(consumerFactory());

    return factory;
  }
}
