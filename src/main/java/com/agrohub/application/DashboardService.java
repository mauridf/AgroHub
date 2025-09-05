package com.agrohub.application;

import com.agrohub.domain.*;
import com.agrohub.web.dto.DashboardDTOs;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DashboardService {

    private final ProdutorService produtorService;
    private final CulturaService culturaPlantadaService;
    private final CustoOperacionalService custoService;
    private final VendaProducaoService vendaService;
    private final ProdutorService fazendaService;

    public DashboardService(ProdutorService produtorService,
                            CulturaService culturaPlantadaService,
                            CustoOperacionalService custoService,
                            VendaProducaoService vendaService,
                            ProdutorService fazendaService) {
        this.produtorService = produtorService;
        this.culturaPlantadaService = culturaPlantadaService;
        this.custoService = custoService;
        this.vendaService = vendaService;
        this.fazendaService = fazendaService;
    }

    public List<DashboardDTOs.ProducaoPorSafra> producaoPorSafra(UUID produtorId) {
        var culturas = culturaPlantadaService.findByProdutor(produtorId);
        return culturas.stream()
                .collect(Collectors.groupingBy(c -> c.getSafra(),
                        Collectors.summingDouble(c -> Optional.ofNullable(c.getProdutividadeObtidaSacasHa())
                                .orElse(0.0) * c.getAreaPlantadaHa())))
                .entrySet().stream()
                .map(e -> new DashboardDTOs.ProducaoPorSafra(e.getKey(), e.getValue()))
                .toList();
    }

    public List<DashboardDTOs.RentabilidadePorHectare> rentabilidadePorHectare(UUID produtorId) {
        var fazendas = fazendaService.findByProdutor(produtorId);
        List<DashboardDTOs.RentabilidadePorHectare> result = new ArrayList<>();
        for (Fazenda f : fazendas) {
            double receitaTotal = vendaService.findByFazenda(f.getId()).stream()
                    .mapToDouble(v -> v.getQuantidadeVendida() * v.getPrecoUnitario())
                    .sum();
            double custoTotal = custoService.findByFazenda(f).stream()
                    .mapToDouble(CustoOperacional::getValor)
                    .sum();
            double area = Optional.ofNullable(f.getAreaAgricultavelHa()).orElse(1.0);
            result.add(new DashboardDTOs.RentabilidadePorHectare(f.getNome(), (receitaTotal - custoTotal) / area));
        }
        return result;
    }

    public List<DashboardDTOs.CustoReceita> custosXReceita(UUID produtorId) {
        var fazendas = fazendaService.findByProdutor(produtorId);
        List<DashboardDTOs.CustoReceita> result = new ArrayList<>();
        for (Fazenda f : fazendas) {
            double receitaTotal = vendaService.findByFazenda(f.getId()).stream()
                    .mapToDouble(v -> v.getQuantidadeVendida() * v.getPrecoUnitario())
                    .sum();
            double custoTotal = custoService.findByFazenda(f).stream()
                    .mapToDouble(CustoOperacional::getValor)
                    .sum();
            result.add(new DashboardDTOs.CustoReceita(f.getNome(), custoTotal, receitaTotal));
        }
        return result;
    }

    public List<DashboardDTOs.UsoDeAreas> usoDeAreas(UUID produtorId) {
        var fazendas = fazendaService.findByProdutor(produtorId);
        return fazendas.stream()
                .map(f -> new DashboardDTOs.UsoDeAreas(
                        f.getNome(),
                        Optional.ofNullable(f.getAreaAgricultavelHa()).orElse(0.0),
                        Optional.ofNullable(f.getAreaVegetacaoHa()).orElse(0.0)))
                .toList();
    }

    // Podemos adicionar mais métodos para os outros gráficos e previsões
}