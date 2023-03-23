package com.github.dudekmat.usereventstoreservice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "elasticsearch")
@Data
class ElasticsearchProperties {

  private String host;
  private String username;
  private String password;
}
