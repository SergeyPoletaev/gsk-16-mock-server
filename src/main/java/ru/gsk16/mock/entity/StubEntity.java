package ru.gsk16.mock.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@Document("wiremock_stubs")
public class StubEntity {
    @Id
    private UUID id;
    private String mapping;
    private String name;
}
