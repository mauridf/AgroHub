package com.agrohub.web;

import com.agrohub.application.MaquinasAgricolasService;
import com.agrohub.application.ProdutorService;
import com.agrohub.domain.MaquinasAgricolas;
import com.agrohub.domain.Fazenda;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/maquinas")
public class MaquinasAgricolasController {

    private final MaquinasAgricolasService service;
    private final ProdutorService produtorService;

    public MaquinasAgricolasController(MaquinasAgricolasService service, ProdutorService produtorService) {
        this.service = service;
        this.produtorService = produtorService;
    }

    @GetMapping
    public ResponseEntity<List<MaquinasAgricolas>> listAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/fazenda/{fazendaId}")
    public ResponseEntity<List<MaquinasAgricolas>> listByFazenda(@PathVariable UUID fazendaId) {
        Fazenda fazenda = produtorService.findFazendaById(fazendaId);
        return ResponseEntity.ok(service.findByFazenda(fazenda));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaquinasAgricolas> get(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<MaquinasAgricolas> create(@RequestBody MaquinasAgricolas maquina) {
        return ResponseEntity.ok(service.save(maquina));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MaquinasAgricolas> update(@PathVariable UUID id, @RequestBody MaquinasAgricolas maquina) {
        maquina.setId(id);
        return ResponseEntity.ok(service.save(maquina));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
