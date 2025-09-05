package com.agrohub.web;

import com.agrohub.application.InsumoService;
import com.agrohub.domain.CompraInsumo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/compras-insumo")
public class CompraInsumoController {

    private final InsumoService service;

    public CompraInsumoController(InsumoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<CompraInsumo>> listAll() {
        // Se quiser, podemos adicionar filtro por fazenda depois
        return ResponseEntity.ok(service.findAllCompras());
    }

    @PostMapping
    public ResponseEntity<CompraInsumo> create(@RequestBody CompraInsumo compra) {
        return ResponseEntity.ok(service.registrarCompra(compra));
    }
}
