package ru.gsk16.mock.config;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.gsk16.mock.extension.ConfigLoaderExtension;
import ru.gsk16.mock.extension.MongoLoaderExtension;

@Configuration
@RequiredArgsConstructor
public class WireMockConfig {

    private final WireMockProperties properties;
    private final MongoLoaderExtension mongoLoaderExtension;
    private final ConfigLoaderExtension configLoaderExtension;

    @Bean(initMethod = "start", destroyMethod = "stop")
    public WireMockServer wireMockServer(WireMockConfiguration configuration) {
        return new WireMockServer(configuration);
    }

    @Bean
    public WireMockConfiguration wireMockConfiguration() {
        return new WireMockConfiguration()
                .port(properties.port())
                .extensions(configLoaderExtension, mongoLoaderExtension);
    }
}
