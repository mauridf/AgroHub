package com.agrohub.web;

import com.agrohub.application.VendaProducaoService;
import com.agrohub.domain.VendaProducao;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/vendas")
public class VendaProducaoController {

    private final VendaProducaoService service;

    public VendaProducaoController(VendaProducaoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<VendaProducao>> listAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendaProducao> get(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<VendaProducao> create(@RequestBody VendaProducao venda) {
        return ResponseEntity.ok(service.save(venda));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VendaProducao> update(@PathVariable UUID id, @RequestBody VendaProducao venda) {
        venda.setId(id);
        return ResponseEntity.ok(service.save(venda));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
