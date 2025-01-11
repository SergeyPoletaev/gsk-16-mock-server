package ru.gsk16.mock.extension;

import com.github.tomakehurst.wiremock.extension.MappingsLoaderExtension;
import com.github.tomakehurst.wiremock.stubbing.StubMapping;
import com.github.tomakehurst.wiremock.stubbing.StubMappings;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.gsk16.mock.config.WireMockProperties;

@Slf4j
@Component
@RequiredArgsConstructor
public class ConfigLoaderExtension implements MappingsLoaderExtension {

    private final WireMockProperties wireMockProperties;

    @Override
    public void loadMappingsInto(StubMappings stubMappings) {
        for (String mapping : wireMockProperties.mappings()) {
            try {
                var stubMapping = StubMapping.buildFrom(mapping);
                stubMappings.addMapping(stubMapping);
            } catch (Exception exception) {
                log.error("Ошибка при загрузке из конфигурации", exception);
            }
        }
    }

    @Override
    public String getName() {
        return "WireMockConfigLoaderExtension";
    }
}
