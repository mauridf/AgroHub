package com.agrohub.web;

import com.agrohub.application.ProdutorService;
import com.agrohub.domain.Fazenda;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/fazendas")
public class FazendaController {

    private final ProdutorService service;

    public FazendaController(ProdutorService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Fazenda>> listAll() {
        return ResponseEntity.ok(service.findAllFazendas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fazenda> get(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findFazendaById(id));
    }

    @PostMapping
    public ResponseEntity<Fazenda> create(@RequestBody Fazenda fazenda) {
        return ResponseEntity.ok(service.saveFazenda(fazenda));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Fazenda> update(@PathVariable UUID id, @RequestBody Fazenda fazenda) {
        fazenda.setId(id);
        return ResponseEntity.ok(service.saveFazenda(fazenda));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.deleteFazenda(id);
        return ResponseEntity.noContent().build();
    }
}

