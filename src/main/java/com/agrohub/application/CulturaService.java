package com.agrohub.application;

import com.agrohub.domain.Cultura;
import com.agrohub.domain.CulturaPlantada;
import com.agrohub.domain.Fazenda;
import com.agrohub.domain.Produtor;
import com.agrohub.repository.CulturaPlantadaRepository;
import com.agrohub.repository.CulturaRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.UUID;

@Service
public class CulturaService {

    private final CulturaRepository culturaRepository;
    private final CulturaPlantadaRepository plantadaRepository;
    private final ProdutorService produtorService; // necessário para buscar fazendas do produtor

    public CulturaService(CulturaRepository culturaRepository,
                          CulturaPlantadaRepository plantadaRepository,
                          ProdutorService produtorService) {
        this.culturaRepository = culturaRepository;
        this.plantadaRepository = plantadaRepository;
        this.produtorService = produtorService;
    }

    // CRUD Cultura
    public List<Cultura> findAllCulturas() {
        return culturaRepository.findAll();
    }

    public Cultura findCulturaById(UUID id) {
        return culturaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cultura não encontrada"));
    }

    public Cultura saveCultura(Cultura cultura) {
        return culturaRepository.save(cultura);
    }

    public void deleteCultura(UUID id) {
        culturaRepository.deleteById(id);
    }

    // CRUD CulturaPlantada (por fazenda / por produtor)
    public List<CulturaPlantada> findByFazenda(Fazenda fazenda) {
        return plantadaRepository.findByFazenda(fazenda);
    }

    // Overload: aceitar fazendaId
    public List<CulturaPlantada> findByFazenda(UUID fazendaId) {
        // evita dependência direta com FazendaRepository aqui — assume que chamante tem o objeto Fazenda
        // se preferir, adicione FazendaService para buscar por id
        throw new UnsupportedOperationException("Use findByFazenda(Fazenda) ou adicione FazendaService para buscar por id.");
    }

    // Novo: buscar todas culturas plantadas de um produtor (por produtorId)
    public List<CulturaPlantada> findByProdutor(UUID produtorId) {
        var produtor = produtorService.findById(produtorId);
        List<Fazenda> fazendas = produtorService.findFazendasByProdutor(produtor);
        return fazendas.stream()
                .flatMap(f -> plantadaRepository.findByFazenda(f).stream())
                .collect(Collectors.toList());
    }

    public CulturaPlantada saveCulturaPlantada(CulturaPlantada plantada) {
        // validações (datas, area positiva etc.) podem ser adicionadas aqui
        return plantadaRepository.save(plantada);
    }

    public CulturaPlantada findCulturaPlantadaById(UUID id) {
        return plantadaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cultura Plantada não encontrada"));
    }

    public void deleteCulturaPlantada(UUID id) {
        plantadaRepository.deleteById(id);
    }
}
