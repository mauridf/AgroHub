package com.agrohub.repository;

import com.agrohub.domain.Contrato;
import com.agrohub.domain.Fazenda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ContratoRepository extends JpaRepository<Contrato, UUID> {
    List<Contrato> findByFazenda(Fazenda fazenda);
}
