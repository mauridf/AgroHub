package com.agrohub.repository;

import com.agrohub.domain.Clima;
import com.agrohub.domain.Fazenda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface ClimaRepository extends JpaRepository<Clima, UUID> {
    List<Clima> findByFazenda(Fazenda fazenda);
    List<Clima> findByFazendaAndDataBetween(Fazenda fazenda, LocalDate start, LocalDate end);
}
