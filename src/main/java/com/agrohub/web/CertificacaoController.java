package com.agrohub.web;

import com.agrohub.application.CertificacaoService;
import com.agrohub.application.ProdutorService;
import com.agrohub.domain.Certificacao;
import com.agrohub.domain.Fazenda;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/certificacoes")
public class CertificacaoController {

    private final CertificacaoService service;
    private final ProdutorService produtorService;

    public CertificacaoController(CertificacaoService service, ProdutorService produtorService) {
        this.service = service;
        this.produtorService = produtorService;
    }

    @GetMapping
    public ResponseEntity<List<Certificacao>> listAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/fazenda/{fazendaId}")
    public ResponseEntity<List<Certificacao>> listByFazenda(@PathVariable UUID fazendaId) {
        Fazenda fazenda = produtorService.findFazendaById(fazendaId);
        return ResponseEntity.ok(service.findByFazenda(fazenda));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Certificacao> get(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Certificacao> create(@RequestBody Certificacao cert) {
        return ResponseEntity.ok(service.save(cert));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Certificacao> update(@PathVariable UUID id, @RequestBody Certificacao cert) {
        cert.setId(id);
        return ResponseEntity.ok(service.save(cert));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
