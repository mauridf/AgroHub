package com.agrohub.repository;

import com.agrohub.domain.EstoqueInsumo;
import com.agrohub.domain.Fazenda;
import com.agrohub.domain.Insumo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EstoqueInsumoRepository extends JpaRepository<EstoqueInsumo, UUID> {
    List<EstoqueInsumo> findByFazenda(Fazenda fazenda);
    Optional<EstoqueInsumo> findByFazendaAndInsumo(Fazenda fazenda, Insumo insumo);
}
