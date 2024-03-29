package com.github.dudekmat.usereventstoreservice.config.elasticsearch;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ElasticsearchProperties.class)
@RequiredArgsConstructor
public class ElasticsearchConfig {

  private final ElasticsearchProperties elasticsearchProperties;

  public static final String PRODUCT_EVENT_INDEX = "product-event";
  public static final String SEARCH_EVENT_INDEX = "search-event";

  @Bean
  public ElasticsearchClient elasticsearchClient() {

    final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
    credentialsProvider.setCredentials(AuthScope.ANY,
        new UsernamePasswordCredentials(elasticsearchProperties.username(),
            elasticsearchProperties.password()));

    RestClient restClient = RestClient.builder(HttpHost.create(elasticsearchProperties.host()))
        .setHttpClientConfigCallback(httpClientBuilder ->
            httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider))
        .build();

    ElasticsearchTransport transport = new RestClientTransport(restClient,
        new JacksonJsonpMapper());

    return new ElasticsearchClient(transport);
  }
}
