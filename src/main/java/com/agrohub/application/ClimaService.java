package com.agrohub.application;

import com.agrohub.domain.Clima;
import com.agrohub.domain.Fazenda;
import com.agrohub.repository.ClimaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class ClimaService {

    private final ClimaRepository repository;

    public ClimaService(ClimaRepository repository) {
        this.repository = repository;
    }

    public List<Clima> findAll() {
        return repository.findAll();
    }

    public List<Clima> findByFazenda(Fazenda fazenda) {
        return repository.findByFazenda(fazenda);
    }

    public List<Clima> findByFazendaAndPeriodo(Fazenda fazenda, LocalDate start, LocalDate end) {
        return repository.findByFazendaAndDataBetween(fazenda, start, end);
    }

    public Clima findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro de clima não encontrado"));
    }

    public Clima save(Clima clima) {
        return repository.save(clima);
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
