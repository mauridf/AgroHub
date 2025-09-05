package com.agrohub.repository;

import com.agrohub.domain.CustoOperacional;
import com.agrohub.domain.Fazenda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CustoOperacionalRepository extends JpaRepository<CustoOperacional, UUID> {
    List<CustoOperacional> findByFazenda(Fazenda fazenda);
}
