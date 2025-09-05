package com.agrohub.web;

import com.agrohub.application.InsumoService;
import com.agrohub.domain.Insumo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/insumos")
public class InsumoController {

    private final InsumoService service;

    public InsumoController(InsumoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Insumo>> listAll() {
        return ResponseEntity.ok(service.findAllInsumos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Insumo> get(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findInsumoById(id));
    }

    @PostMapping
    public ResponseEntity<Insumo> create(@RequestBody Insumo insumo) {
        return ResponseEntity.ok(service.saveInsumo(insumo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Insumo> update(@PathVariable UUID id, @RequestBody Insumo insumo) {
        insumo.setId(id);
        return ResponseEntity.ok(service.saveInsumo(insumo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.deleteInsumo(id);
        return ResponseEntity.noContent().build();
    }
}
