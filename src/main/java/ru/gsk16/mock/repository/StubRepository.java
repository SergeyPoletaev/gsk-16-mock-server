package ru.gsk16.mock.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.gsk16.mock.entity.StubEntity;

import java.util.Optional;
import java.util.UUID;

public interface StubRepository extends MongoRepository<StubEntity, UUID> {
    Optional<StubEntity> findByName(String name);
}
