package com.agrohub.web;

import com.agrohub.application.ProdutorService;
import com.agrohub.domain.Produtor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/produtores")
public class ProdutorController {

    private final ProdutorService service;

    public ProdutorController(ProdutorService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Produtor>> listAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produtor> get(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Produtor> create(@RequestBody Produtor produtor) {
        return ResponseEntity.ok(service.saveProdutor(produtor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produtor> update(@PathVariable UUID id, @RequestBody Produtor produtor) {
        produtor.setId(id);
        return ResponseEntity.ok(service.saveProdutor(produtor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.deleteProdutor(id);
        return ResponseEntity.noContent().build();
    }
}

