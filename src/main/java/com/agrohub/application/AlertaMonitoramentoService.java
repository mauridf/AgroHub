package com.agrohub.application;

import com.agrohub.domain.AlertaMonitoramento;
import com.agrohub.domain.Fazenda;
import com.agrohub.repository.AlertaMonitoramentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AlertaMonitoramentoService {

    private final AlertaMonitoramentoRepository repository;

    public AlertaMonitoramentoService(AlertaMonitoramentoRepository repository) {
        this.repository = repository;
    }

    public List<AlertaMonitoramento> findAll() {
        return repository.findAll();
    }

    public List<AlertaMonitoramento> findByFazenda(Fazenda fazenda) {
        return repository.findByFazenda(fazenda);
    }

    public List<AlertaMonitoramento> findByFazendaAndResolvido(Fazenda fazenda, Boolean resolvido) {
        return repository.findByFazendaAndResolvido(fazenda, resolvido);
    }

    public AlertaMonitoramento findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alerta não encontrado"));
    }

    public AlertaMonitoramento save(AlertaMonitoramento alerta) {
        return repository.save(alerta);
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
