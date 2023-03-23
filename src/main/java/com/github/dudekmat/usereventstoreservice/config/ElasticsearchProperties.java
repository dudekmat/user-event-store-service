package com.github.dudekmat.usereventstoreservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "elasticsearch")
record ElasticsearchProperties(String host, String username, String password) {

}
