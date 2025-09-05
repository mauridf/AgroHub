package com.agrohub.web;

import com.agrohub.application.CustoOperacionalService;
import com.agrohub.application.ProdutorService;
import com.agrohub.domain.CustoOperacional;
import com.agrohub.domain.Fazenda;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/custos")
public class CustoOperacionalController {

    private final CustoOperacionalService service;
    private final ProdutorService produtorService;

    public CustoOperacionalController(CustoOperacionalService service, ProdutorService produtorService) {
        this.service = service;
        this.produtorService = produtorService;
    }

    @GetMapping
    public ResponseEntity<List<CustoOperacional>> listAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/fazenda/{fazendaId}")
    public ResponseEntity<List<CustoOperacional>> listByFazenda(@PathVariable UUID fazendaId) {
        Fazenda fazenda = produtorService.findFazendaById(fazendaId);
        return ResponseEntity.ok(service.findByFazenda(fazenda));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustoOperacional> get(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<CustoOperacional> create(@RequestBody CustoOperacional custo) {
        return ResponseEntity.ok(service.save(custo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustoOperacional> update(@PathVariable UUID id, @RequestBody CustoOperacional custo) {
        custo.setId(id);
        return ResponseEntity.ok(service.save(custo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
