package ru.gsk16.mock.service;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.stubbing.StubMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gsk16.mock.entity.StubEntity;
import ru.gsk16.mock.repository.StubRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WireMockPersistentService {

    private final StubRepository repository;
    private final WireMockServer wireMockServer;

    public List<StubMapping> getAll() {
        return wireMockServer.listAllStubMappings().getMappings();
    }

    public void save(String mapping) {
        var stubMapping = StubMapping.buildFrom(mapping);
        var name = stubMapping.getMetadata().getString("name");
        repository.findByName(name)
                .ifPresent(stub -> deleteById(stub.getId()));
        repository.save(new StubEntity().setId(stubMapping.getId()).setMapping(mapping).setName(name));
        wireMockServer.addStubMapping(stubMapping);
    }

    public void deleteById(UUID id) {
        repository.deleteById(id);
        wireMockServer.removeStub(id);
    }
}
