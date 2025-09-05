package com.agrohub.repository;

import com.agrohub.domain.CulturaPlantada;
import com.agrohub.domain.Fazenda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CulturaPlantadaRepository extends JpaRepository<CulturaPlantada, UUID> {
    List<CulturaPlantada> findByFazenda(Fazenda fazenda);
}
