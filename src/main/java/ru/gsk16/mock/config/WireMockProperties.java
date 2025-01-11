package ru.gsk16.mock.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "wiremock")
public record WireMockProperties(int port, List<String> mappings) {
}
