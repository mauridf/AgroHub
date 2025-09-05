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

    public List<Map<String, Object>> evolucaoHistoricaProdutividade(UUID produtorId) {
        var culturas = culturaPlantadaService.findByProdutor(produtorId);
        Map<String, Map<String, Double>> evolucao = new TreeMap<>(); // Safra -> Cultura -> Produtividade

        for (var c : culturas) {
            String safra = c.getSafra();
            String cultura = c.getCultura().getNome();
            double produtividade = Optional.ofNullable(c.getProdutividadeObtidaSacasHa()).orElse(0.0);

            evolucao.computeIfAbsent(safra, k -> new HashMap<>());
            evolucao.get(safra).put(cultura, produtividade);
        }

        List<Map<String, Object>> result = new ArrayList<>();
        for (var entry : evolucao.entrySet()) {
            Map<String, Object> map = new HashMap<>();
            map.put("safra", entry.getKey());
            map.put("produtividades", entry.getValue());
            result.add(map);
        }
        return result;
    }

    public List<Map<String, Object>> distribuicaoCulturas(UUID produtorId) {
        var fazendas = fazendaService.findByProdutor(produtorId);
        List<Map<String, Object>> result = new ArrayList<>();

        for (var f : fazendas) {
            for (var cp : culturaPlantadaService.findByFazenda(f.getId())) {
                Map<String, Object> map = new HashMap<>();
                map.put("fazenda", f.getNome());
                map.put("cultura", cp.getCultura().getNome());
                map.put("latitude", f.getLatitude());
                map.put("longitude", f.getLongitude());
                map.put("areaPlantadaHa", cp.getAreaPlantadaHa());
                result.add(map);
            }
        }
        return result;
    }

    public List<Map<String, Object>> estoqueInsumosPorSafra(UUID produtorId) {
        var fazendas = fazendaService.findByProdutor(produtorId);
        List<Map<String, Object>> result = new ArrayList<>();

        for (var f : fazendas) {
            for (var ei : estoqueInsumoService.findByFazenda(f.getId())) {
                Map<String, Object> map = new HashMap<>();
                map.put("fazenda", f.getNome());
                map.put("insumo", ei.getInsumo().getNome());
                map.put("quantidade", ei.getQuantidade());
                map.put("validade", ei.getValidade());
                result.add(map);
            }
        }
        return result;
    }

    public List<Map<String, Object>> eficienciaPorFazenda(UUID produtorId) {
        var fazendas = fazendaService.findByProdutor(produtorId);
        List<Map<String, Object>> result = new ArrayList<>();

        for (var f : fazendas) {
            Map<String, Object> map = new HashMap<>();
            int funcionarios = funcionarioService.findByFazenda(f.getId()).size();
            int maquinas = maquinasAgricolasService.findByFazenda(f.getId()).size();
            map.put("fazenda", f.getNome());
            map.put("funcionarios", funcionarios);
            map.put("maquinas", maquinas);
            result.add(map);
        }
        return result;
    }

    public List<Map<String, Object>> previsaoReceitaFutura(UUID produtorId) {
        var fazendas = fazendaService.findByProdutor(produtorId);
        List<Map<String, Object>> result = new ArrayList<>();

        for (var f : fazendas) {
            double receitaEsperada = culturaPlantadaService.findByFazenda(f.getId()).stream()
                    .mapToDouble(cp -> Optional.ofNullable(cp.getProdutividadeEsperadaSacasHa()).orElse(0.0) * cp.getAreaPlantadaHa())
                    .sum();
            Map<String, Object> map = new HashMap<>();
            map.put("fazenda", f.getNome());
            map.put("receitaEsperada", receitaEsperada);
            result.add(map);
        }
        return result;
    }

    public List<Map<String, Object>> fazendasPorEstado(UUID produtorId) {
        var fazendas = fazendaService.findByProdutor(produtorId);
        Map<String, Long> agrupado = fazendas.stream()
                .collect(Collectors.groupingBy(Fazenda::getEstado, Collectors.counting()));
        return agrupado.entrySet().stream()
                .map(e -> Map.of("estado", e.getKey(), "quantidade", e.getValue()))
                .toList();
    }
}