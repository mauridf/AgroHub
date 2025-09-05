package com.agrohub.application;

import com.agrohub.domain.Contrato;
import com.agrohub.domain.Fazenda;
import com.agrohub.repository.ContratoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ContratoService {

    private final ContratoRepository repository;

    public ContratoService(ContratoRepository repository) {
        this.repository = repository;
    }

    public List<Contrato> findAll() {
        return repository.findAll();
    }

    public List<Contrato> findByFazenda(Fazenda fazenda) {
        return repository.findByFazenda(fazenda);
    }

    public Contrato findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contrato não encontrado"));
    }

    public Contrato save(Contrato contrato) {
        return repository.save(contrato);
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
