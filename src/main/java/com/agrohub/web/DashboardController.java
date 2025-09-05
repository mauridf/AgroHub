package com.agrohub.web;

import com.agrohub.application.DashboardService;
import com.agrohub.web.dto.ProducaoSafraDTO;
import com.agrohub.web.dto.UsoAreasDTO;
import com.agrohub.web.dto.RentabilidadeDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService service;

    public DashboardController(DashboardService service) {
        this.service = service;
    }

    @GetMapping("/producao-safra")
    public ResponseEntity<List<ProducaoSafraDTO>> producaoPorSafra() {
        return ResponseEntity.ok(service.producaoPorSafra());
    }

    @GetMapping("/uso-areas")
    public ResponseEntity<List<UsoAreasDTO>> usoDeAreas() {
        return ResponseEntity.ok(service.usoDeAreas());
    }

    @GetMapping("/rentabilidade")
    public ResponseEntity<List<RentabilidadeDTO>> rentabilidade() {
        return ResponseEntity.ok(service.rentabilidadePorHectare());
    }
}

