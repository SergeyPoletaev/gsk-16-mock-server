package ru.gsk16.mock.extension;

import com.github.tomakehurst.wiremock.extension.MappingsLoaderExtension;
import com.github.tomakehurst.wiremock.stubbing.StubMapping;
import com.github.tomakehurst.wiremock.stubbing.StubMappings;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.gsk16.mock.entity.StubEntity;
import ru.gsk16.mock.repository.StubRepository;

@Slf4j
@Component
@RequiredArgsConstructor
public class MongoLoaderExtension implements MappingsLoaderExtension {

    private final StubRepository stubRepository;

    @Override
    public void loadMappingsInto(StubMappings stubMappings) {
        for (StubEntity stubEntity : stubRepository.findAll()) {
            try {
                var stubMapping = StubMapping.buildFrom(stubEntity.getMapping());
                stubMapping.setId(stubEntity.getId());
                stubMappings.addMapping(stubMapping);
            } catch (Exception exception) {
                log.error("Ошибка при загрузке из БД", exception);
            }
        }
    }

    @Override
    public String getName() {
        return "MongoLoaderExtension";
    }
}
