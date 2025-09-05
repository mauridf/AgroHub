package com.agrohub.application;

import com.agrohub.domain.*;
import com.agrohub.web.dto.DashboardDTOs;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.UUID;

@Service
public class DashboardService {

    private final ProdutorService produtorService;
    private final CulturaService culturaService;
    private final CustoOperacionalService custoService;
    private final VendaProducaoService vendaService;
    private final InsumoService insumoService;
    private final FuncionarioService funcionarioService;
    private final MaquinasAgricolasService maquinasAgricolasService;

    public DashboardService(ProdutorService produtorService,
                            CulturaService culturaService,
                            CustoOperacionalService custoService,
                            VendaProducaoService vendaService,
                            InsumoService insumoService,
                            FuncionarioService funcionarioService,
                            MaquinasAgricolasService maquinasAgricolasService) {
        this.produtorService = produtorService;
        this.culturaService = culturaService;
        this.custoService = custoService;
        this.vendaService = vendaService;
        this.insumoService = insumoService;
        this.funcionarioService = funcionarioService;
        this.maquinasAgricolasService = maquinasAgricolasService;
    }

    // Produção por safra (por produtor)
    public List<DashboardDTOs.ProducaoPorSafra> producaoPorSafra(UUID produtorId) {
        var culturas = culturaService.findByProdutor(produtorId);
        return culturas.stream()
                .collect(Collectors.groupingBy(CulturaPlantada::getSafra,
                        Collectors.summingDouble(c -> Optional.ofNullable(c.getProdutividadeObtidaSacasHa()).orElse(0.0) * Optional.ofNullable(c.getAreaPlantadaHa()).orElse(0.0))))
                .entrySet().stream()
                .map(e -> new DashboardDTOs.ProducaoPorSafra(e.getKey(), e.getValue()))
                .collect(Collectors.toList());
    }

    // Rentabilidade por hectare (por produtor)
    public List<DashboardDTOs.RentabilidadePorHectare> rentabilidadePorHectare(UUID produtorId) {
        Produtor produtor = produtorService.findById(produtorId);
        List<Fazenda> fazendas = produtorService.findFazendasByProdutor(produtor);
        List<DashboardDTOs.RentabilidadePorHectare> result = new ArrayList<>();
        for (Fazenda f : fazendas) {
            double receitaTotal = vendaService.findByFazenda(f.getId()).stream()
                    .mapToDouble(v -> Optional.ofNullable(v.getQuantidadeVendida()).orElse(0.0) * Optional.ofNullable(v.getPrecoUnitario()).orElse(0.0))
                    .sum();
            double custoTotal = custoService.findByFazenda(f).stream()
                    .mapToDouble(c -> Optional.ofNullable(c.getValor()).orElse(0.0))
                    .sum();
            double area = Optional.ofNullable(f.getAreaAgricultavelHa()).orElse(1.0);
            result.add(new DashboardDTOs.RentabilidadePorHectare(f.getNome(), (receitaTotal - custoTotal) / area));
        }
        return result;
    }

    // Custos x Receita
    public List<DashboardDTOs.CustoReceita> custosXReceita(UUID produtorId) {
        Produtor produtor = produtorService.findById(produtorId);
        List<Fazenda> fazendas = produtorService.findFazendasByProdutor(produtor);
        List<DashboardDTOs.CustoReceita> result = new ArrayList<>();
        for (Fazenda f : fazendas) {
            double receitaTotal = vendaService.findByFazenda(f.getId()).stream()
                    .mapToDouble(v -> Optional.ofNullable(v.getQuantidadeVendida()).orElse(0.0) * Optional.ofNullable(v.getPrecoUnitario()).orElse(0.0))
                    .sum();
            double custoTotal = custoService.findByFazenda(f).stream()
                    .mapToDouble(c -> Optional.ofNullable(c.getValor()).orElse(0.0))
                    .sum();
            result.add(new DashboardDTOs.CustoReceita(f.getNome(), custoTotal, receitaTotal));
        }
        return result;
    }

    // Uso de áreas
    public List<DashboardDTOs.UsoDeAreas> usoDeAreas(UUID produtorId) {
        Produtor produtor = produtorService.findById(produtorId);
        List<Fazenda> fazendas = produtorService.findFazendasByProdutor(produtor);
        return fazendas.stream()
                .map(f -> new DashboardDTOs.UsoDeAreas(
                        f.getNome(),
                        Optional.ofNullable(f.getAreaAgricultavelHa()).orElse(0.0),
                        Optional.ofNullable(f.getAreaVegetacaoHa()).orElse(0.0)))
                .collect(Collectors.toList());
    }

    // Evolução histórica de produtividade
    public List<Map<String, Object>> evolucaoHistoricaProdutividade(UUID produtorId) {
        var culturas = culturaService.findByProdutor(produtorId);
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

    // Distribuição de culturas no território
    public List<Map<String, Object>> distribuicaoCulturas(UUID produtorId) {
        Produtor produtor = produtorService.findById(produtorId);
        List<Fazenda> fazendas = produtorService.findFazendasByProdutor(produtor);
        List<Map<String, Object>> result = new ArrayList<>();

        for (var f : fazendas) {
            var cps = culturaService.findByFazenda(f); // método existia
            for (var cp : cps) {
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

    // Estoque de insumos por fazenda
    public List<Map<String, Object>> estoqueInsumosPorSafra(UUID produtorId) {
        Produtor produtor = produtorService.findById(produtorId);
        List<Fazenda> fazendas = produtorService.findFazendasByProdutor(produtor);
        List<Map<String, Object>> result = new ArrayList<>();

        for (var f : fazendas) {
            var estoques = insumoService.findEstoqueByFazenda(f); // <- já existe no InsumoService
            for (var ei : estoques) {
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

    // Eficiência de funcionários e maquinário
    public List<Map<String, Object>> eficienciaPorFazenda(UUID produtorId) {
        Produtor produtor = produtorService.findById(produtorId);
        List<Fazenda> fazendas = produtorService.findFazendasByProdutor(produtor);
        List<Map<String, Object>> result = new ArrayList<>();

        for (var f : fazendas) {
            Map<String, Object> map = new HashMap<>();
            int funcionarios = funcionarioService.findByFazenda(f).size();
            int maquinas = maquinasAgricolasService.findByFazenda(f).size();
            map.put("fazenda", f.getNome());
            map.put("funcionarios", funcionarios);
            map.put("maquinas", maquinas);
            result.add(map);
        }
        return result;
    }

    // Previsão de receita futura
    public List<Map<String, Object>> previsaoReceitaFutura(UUID produtorId) {
        Produtor produtor = produtorService.findById(produtorId);
        List<Fazenda> fazendas = produtorService.findFazendasByProdutor(produtor);
        List<Map<String, Object>> result = new ArrayList<>();

        for (var f : fazendas) {
            double receitaEsperada = culturaService.findByFazenda(f).stream()
                    .mapToDouble(cp -> Optional.ofNullable(cp.getProdutividadeEsperadaSacasHa()).orElse(0.0) * Optional.ofNullable(cp.getAreaPlantadaHa()).orElse(0.0))
                    .sum();
            Map<String, Object> map = new HashMap<>();
            map.put("fazenda", f.getNome());
            map.put("receitaEsperada", receitaEsperada);
            result.add(map);
        }
        return result;
    }

    // Fazendas por estado (pizza)
    public List<Map<String, Object>> fazendasPorEstado(UUID produtorId) {
        Produtor produtor = produtorService.findById(produtorId);
        List<Fazenda> fazendas = produtorService.findFazendasByProdutor(produtor);
        Map<String, Long> agrupado = fazendas.stream()
                .collect(Collectors.groupingBy(Fazenda::getEstado, Collectors.counting()));
        return agrupado.entrySet().stream().map(e -> {
            Map<String, Object> map = new HashMap<>();
            map.put("estado", e.getKey());
            map.put("quantidade", e.getValue());
            return map;
        }).collect(Collectors.toList());
    }
}
