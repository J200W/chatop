package com.chatop.api;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * The custom properties class
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "com.chatop.api")
public class CustomProperties {

    /**
     * The API URL
     */
    private String apiUrl;

}