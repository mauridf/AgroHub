package com.agrohub.repository;

import com.agrohub.domain.CompraInsumo;
import com.agrohub.domain.Fazenda;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface CompraInsumoRepository extends JpaRepository<CompraInsumo, UUID> {
    List<CompraInsumo> findByFazenda(Fazenda fazenda);
}
