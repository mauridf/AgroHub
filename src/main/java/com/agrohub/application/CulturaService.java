package com.agrohub.application;

import com.agrohub.domain.Cultura;
import com.agrohub.domain.CulturaPlantada;
import com.agrohub.domain.Fazenda;
import com.agrohub.repository.CulturaPlantadaRepository;
import com.agrohub.repository.CulturaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CulturaService {

    private final CulturaRepository culturaRepository;
    private final CulturaPlantadaRepository plantadaRepository;

    public CulturaService(CulturaRepository culturaRepository,
                          CulturaPlantadaRepository plantadaRepository) {
        this.culturaRepository = culturaRepository;
        this.plantadaRepository = plantadaRepository;
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

    // CRUD CulturaPlantada
    public List<CulturaPlantada> findByFazenda(Fazenda fazenda) {
        return plantadaRepository.findByFazenda(fazenda);
    }

    public CulturaPlantada saveCulturaPlantada(CulturaPlantada plantada) {
        // Aqui podemos adicionar validações adicionais futuramente, ex: datas e produtividade
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
