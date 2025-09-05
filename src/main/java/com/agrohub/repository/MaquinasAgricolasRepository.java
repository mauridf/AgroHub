package com.agrohub.repository;

import com.agrohub.domain.MaquinasAgricolas;
import com.agrohub.domain.Fazenda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MaquinasAgricolasRepository extends JpaRepository<MaquinasAgricolas, UUID> {
    List<MaquinasAgricolas> findByFazenda(Fazenda fazenda);
}
