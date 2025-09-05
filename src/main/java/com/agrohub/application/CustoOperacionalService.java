package com.agrohub.application;

import com.agrohub.domain.CustoOperacional;
import com.agrohub.domain.Fazenda;
import com.agrohub.repository.CustoOperacionalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustoOperacionalService {

    private final CustoOperacionalRepository repository;

    public CustoOperacionalService(CustoOperacionalRepository repository) {
        this.repository = repository;
    }

    public List<CustoOperacional> findAll() {
        return repository.findAll();
    }

    public List<CustoOperacional> findByFazenda(Fazenda fazenda) {
        return repository.findByFazenda(fazenda);
    }

    public CustoOperacional findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Custo operacional não encontrado"));
    }

    public CustoOperacional save(CustoOperacional custo) {
        return repository.save(custo);
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
