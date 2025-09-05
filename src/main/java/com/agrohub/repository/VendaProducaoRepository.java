package com.agrohub.repository;

import com.agrohub.domain.VendaProducao;
import com.agrohub.domain.CulturaPlantada;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface VendaProducaoRepository extends JpaRepository<VendaProducao, UUID> {
    List<VendaProducao> findByCulturaPlantada(CulturaPlantada culturaPlantada);
}
