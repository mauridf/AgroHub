package com.agrohub.web;

import com.agrohub.application.ContratoService;
import com.agrohub.application.ProdutorService;
import com.agrohub.domain.Contrato;
import com.agrohub.domain.Fazenda;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/contratos")
public class ContratoController {

    private final ContratoService service;
    private final ProdutorService produtorService;

    public ContratoController(ContratoService service, ProdutorService produtorService) {
        this.service = service;
        this.produtorService = produtorService;
    }

    @GetMapping
    public ResponseEntity<List<Contrato>> listAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/fazenda/{fazendaId}")
    public ResponseEntity<List<Contrato>> listByFazenda(@PathVariable UUID fazendaId) {
        Fazenda fazenda = produtorService.findFazendaById(fazendaId);
        return ResponseEntity.ok(service.findByFazenda(fazenda));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contrato> get(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Contrato> create(@RequestBody Contrato contrato) {
        return ResponseEntity.ok(service.save(contrato));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contrato> update(@PathVariable UUID id, @RequestBody Contrato contrato) {
        contrato.setId(id);
        return ResponseEntity.ok(service.save(contrato));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
