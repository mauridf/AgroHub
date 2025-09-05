package com.agrohub.web;

import com.agrohub.application.ClimaService;
import com.agrohub.application.ProdutorService;
import com.agrohub.domain.Clima;
import com.agrohub.domain.Fazenda;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/clima")
public class ClimaController {

    private final ClimaService service;
    private final ProdutorService produtorService;

    public ClimaController(ClimaService service, ProdutorService produtorService) {
        this.service = service;
        this.produtorService = produtorService;
    }

    @GetMapping
    public ResponseEntity<List<Clima>> listAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/fazenda/{fazendaId}")
    public ResponseEntity<List<Clima>> listByFazenda(@PathVariable UUID fazendaId) {
        Fazenda fazenda = produtorService.findFazendaById(fazendaId);
        return ResponseEntity.ok(service.findByFazenda(fazenda));
    }

    @GetMapping("/fazenda/{fazendaId}/periodo")
    public ResponseEntity<List<Clima>> listByFazendaAndPeriodo(
            @PathVariable UUID fazendaId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {

        Fazenda fazenda = produtorService.findFazendaById(fazendaId);
        return ResponseEntity.ok(service.findByFazendaAndPeriodo(fazenda, start, end));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Clima> get(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Clima> create(@RequestBody Clima clima) {
        return ResponseEntity.ok(service.save(clima));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Clima> update(@PathVariable UUID id, @RequestBody Clima clima) {
        clima.setId(id);
        return ResponseEntity.ok(service.save(clima));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
