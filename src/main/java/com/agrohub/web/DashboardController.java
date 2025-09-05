package com.agrohub.web;

import com.agrohub.application.DashboardService;
import com.agrohub.web.dto.DashboardDTOs;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService service;

    public DashboardController(DashboardService service) {
        this.service = service;
    }

    @GetMapping("/producao/{produtorId}")
    public ResponseEntity<List<DashboardDTOs.ProducaoPorSafra>> producaoPorSafra(@PathVariable UUID produtorId) {
        return ResponseEntity.ok(service.producaoPorSafra(produtorId));
    }

    @GetMapping("/rentabilidade/{produtorId}")
    public ResponseEntity<List<DashboardDTOs.RentabilidadePorHectare>> rentabilidadePorHectare(@PathVariable UUID produtorId) {
        return ResponseEntity.ok(service.rentabilidadePorHectare(produtorId));
    }

    @GetMapping("/custos-receita/{produtorId}")
    public ResponseEntity<List<DashboardDTOs.CustoReceita>> custosXReceita(@PathVariable UUID produtorId) {
        return ResponseEntity.ok(service.custosXReceita(produtorId));
    }

    @GetMapping("/uso-areas/{produtorId}")
    public ResponseEntity<List<DashboardDTOs.UsoDeAreas>> usoDeAreas(@PathVariable UUID produtorId) {
        return ResponseEntity.ok(service.usoDeAreas(produtorId));
    }

    @GetMapping("/historico-produtividade/{produtorId}")
    public ResponseEntity<List<Map<String, Object>>> evolucaoHistoricaProdutividade(@PathVariable UUID produtorId) {
        return ResponseEntity.ok(service.evolucaoHistoricaProdutividade(produtorId));
    }

    @GetMapping("/distribuicao-culturas/{produtorId}")
    public ResponseEntity<List<Map<String, Object>>> distribuicaoCulturas(@PathVariable UUID produtorId) {
        return ResponseEntity.ok(service.distribuicaoCulturas(produtorId));
    }

    @GetMapping("/estoque-insumos/{produtorId}")
    public ResponseEntity<List<Map<String, Object>>> estoqueInsumosPorSafra(@PathVariable UUID produtorId) {
        return ResponseEntity.ok(service.estoqueInsumosPorSafra(produtorId));
    }

    @GetMapping("/eficiencia/{produtorId}")
    public ResponseEntity<List<Map<String, Object>>> eficienciaPorFazenda(@PathVariable UUID produtorId) {
        return ResponseEntity.ok(service.eficienciaPorFazenda(produtorId));
    }

    @GetMapping("/previsao-receita/{produtorId}")
    public ResponseEntity<List<Map<String, Object>>> previsaoReceitaFutura(@PathVariable UUID produtorId) {
        return ResponseEntity.ok(service.previsaoReceitaFutura(produtorId));
    }

    @GetMapping("/pizza/fazendas-estado/{produtorId}")
    public ResponseEntity<List<Map<String, Object>>> fazendasPorEstado(@PathVariable UUID produtorId) {
        return ResponseEntity.ok(service.fazendasPorEstado(produtorId));
    }
}
