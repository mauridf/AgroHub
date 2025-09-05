package com.agrohub.repository;

import com.agrohub.domain.CulturaPlantada;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CulturaPlantadaRepository extends JpaRepository<CulturaPlantada, UUID> {
}
