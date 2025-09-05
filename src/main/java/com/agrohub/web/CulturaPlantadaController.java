package com.agrohub.web;

import com.agrohub.application.CulturaService;
import com.agrohub.domain.CulturaPlantada;
import com.agrohub.domain.Fazenda;
import com.agrohub.application.ProdutorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/culturas-plantadas")
public class CulturaPlantadaController {

    private final CulturaService service;
    private final ProdutorService produtorService;

    public CulturaPlantadaController(CulturaService service, ProdutorService produtorService) {
        this.service = service;
        this.produtorService = produtorService;
    }

    @GetMapping("/fazenda/{fazendaId}")
    public ResponseEntity<List<CulturaPlantada>> listByFazenda(@PathVariable UUID fazendaId) {
        Fazenda fazenda = produtorService.findFazendaById(fazendaId);
        return ResponseEntity.ok(service.findByFazenda(fazenda));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CulturaPlantada> get(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findCulturaPlantadaById(id));
    }

    @PostMapping
    public ResponseEntity<CulturaPlantada> create(@RequestBody CulturaPlantada plantada) {
        return ResponseEntity.ok(service.saveCulturaPlantada(plantada));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CulturaPlantada> update(@PathVariable UUID id, @RequestBody CulturaPlantada plantada) {
        plantada.setId(id);
        return ResponseEntity.ok(service.saveCulturaPlantada(plantada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.deleteCulturaPlantada(id);
        return ResponseEntity.noContent().build();
    }
}

