package com.agrohub.web;

import com.agrohub.application.AlertaMonitoramentoService;
import com.agrohub.application.ProdutorService;
import com.agrohub.domain.AlertaMonitoramento;
import com.agrohub.domain.Fazenda;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/alertas")
public class AlertaMonitoramentoController {

    private final AlertaMonitoramentoService service;
    private final ProdutorService produtorService;

    public AlertaMonitoramentoController(AlertaMonitoramentoService service, ProdutorService produtorService) {
        this.service = service;
        this.produtorService = produtorService;
    }

    @GetMapping
    public ResponseEntity<List<AlertaMonitoramento>> listAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/fazenda/{fazendaId}")
    public ResponseEntity<List<AlertaMonitoramento>> listByFazenda(@PathVariable UUID fazendaId) {
        Fazenda fazenda = produtorService.findFazendaById(fazendaId);
        return ResponseEntity.ok(service.findByFazenda(fazenda));
    }

    @GetMapping("/fazenda/{fazendaId}/resolvido")
    public ResponseEntity<List<AlertaMonitoramento>> listByFazendaAndResolvido(
            @PathVariable UUID fazendaId,
            @RequestParam Boolean resolvido) {

        Fazenda fazenda = produtorService.findFazendaById(fazendaId);
        return ResponseEntity.ok(service.findByFazendaAndResolvido(fazenda, resolvido));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlertaMonitoramento> get(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<AlertaMonitoramento> create(@RequestBody AlertaMonitoramento alerta) {
        return ResponseEntity.ok(service.save(alerta));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlertaMonitoramento> update(@PathVariable UUID id, @RequestBody AlertaMonitoramento alerta) {
        alerta.setId(id);
        return ResponseEntity.ok(service.save(alerta));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
