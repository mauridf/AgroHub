package com.agrohub.web;

import com.agrohub.application.CulturaService;
import com.agrohub.domain.Cultura;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/culturas")
public class CulturaController {

    private final CulturaService service;

    public CulturaController(CulturaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Cultura>> listAll() {
        return ResponseEntity.ok(service.findAllCulturas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cultura> get(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findCulturaById(id));
    }

    @PostMapping
    public ResponseEntity<Cultura> create(@RequestBody Cultura cultura) {
        return ResponseEntity.ok(service.saveCultura(cultura));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cultura> update(@PathVariable UUID id, @RequestBody Cultura cultura) {
        cultura.setId(id);
        return ResponseEntity.ok(service.saveCultura(cultura));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.deleteCultura(id);
        return ResponseEntity.noContent().build();
    }
}
