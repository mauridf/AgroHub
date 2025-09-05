package com.agrohub.repository;

import com.agrohub.domain.AlertaMonitoramento;
import com.agrohub.domain.Fazenda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AlertaMonitoramentoRepository extends JpaRepository<AlertaMonitoramento, UUID> {
    List<AlertaMonitoramento> findByFazenda(Fazenda fazenda);
    List<AlertaMonitoramento> findByFazendaAndResolvido(Fazenda fazenda, Boolean resolvido);
}
