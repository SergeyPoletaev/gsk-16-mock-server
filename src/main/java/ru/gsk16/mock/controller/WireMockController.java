package ru.gsk16.mock.controller;

import com.github.tomakehurst.wiremock.common.Json;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gsk16.mock.service.WireMockPersistentService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/wiremock/mappings")
public class WireMockController {

    private final WireMockPersistentService wireMockPersistentService;

    @GetMapping(produces = "application/json")
    public String getAll() {
        return Json.write(wireMockPersistentService.getAll());
    }

    @PostMapping
    public void save(@RequestBody String mapping) {
        wireMockPersistentService.save(mapping);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable UUID id) {
        wireMockPersistentService.deleteById(id);
    }
}
