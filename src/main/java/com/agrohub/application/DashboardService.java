package com.agrohub.application;

import com.agrohub.domain.CulturaPlantada;
import com.agrohub.domain.Fazenda;
import com.agrohub.repository.CulturaPlantadaRepository;
import com.agrohub.repository.FazendaRepository;
import com.agrohub.web.dto.ProducaoSafraDTO;
import com.agrohub.web.dto.UsoAreasDTO;
import com.agrohub.web.dto.RentabilidadeDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DashboardService {

    private final CulturaPlantadaRepository culturaPlantadaRepository;
    private final FazendaRepository fazendaRepository;

    public DashboardService(CulturaPlantadaRepository culturaPlantadaRepository,
                            FazendaRepository fazendaRepository) {
        this.culturaPlantadaRepository = culturaPlantadaRepository;
        this.fazendaRepository = fazendaRepository;
    }

    public List<ProducaoSafraDTO> producaoPorSafra() {
        return culturaPlantadaRepository.findAll().stream()
                .map(c -> new ProducaoSafraDTO(
                        c.getSafra(),
                        c.getFazenda().getNome(),
                        c.getCultura().getNome(),
                        c.getAreaPlantadaHa(),
                        c.getProdutividadeEsperadaSacasHa(),
                        c.getProdutividadeObtidaSacasHa(),
                        c.getReceitaTotal()
                ))
                .collect(Collectors.toList());
    }

    public List<UsoAreasDTO> usoDeAreas() {
        return fazendaRepository.findAll().stream()
                .map(f -> new UsoAreasDTO(
                        f.getNome(),
                        f.getAreaTotalHa(),
                        f.getAreaAgricultavelHa(),
                        f.getAreaVegetacaoHa(),
                        f.getAreaConstruidaHa()
                ))
                .collect(Collectors.toList());
    }

    public List<RentabilidadeDTO> rentabilidadePorHectare() {
        return culturaPlantadaRepository.findAll().stream()
                .map(c -> {
                    double receita = c.getReceitaTotal() != null ? c.getReceitaTotal() : 0;
                    double custo = c.getCustoTotal() != null ? c.getCustoTotal() : 0;
                    double rentabilidadePorHa = c.getAreaPlantadaHa() != null && c.getAreaPlantadaHa() > 0 ?
                            (receita - custo) / c.getAreaPlantadaHa() : 0;
                    return new RentabilidadeDTO(
                            c.getFazenda().getNome(),
                            c.getSafra(),
                            receita,
                            custo,
                            rentabilidadePorHa
                    );
                })
                .collect(Collectors.toList());
    }
}

