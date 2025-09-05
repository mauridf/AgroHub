package com.agrohub.application;

import com.agrohub.domain.MaquinasAgricolas;
import com.agrohub.domain.Fazenda;
import com.agrohub.repository.MaquinasAgricolasRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MaquinasAgricolasService {

    private final MaquinasAgricolasRepository repository;

    public MaquinasAgricolasService(MaquinasAgricolasRepository repository) {
        this.repository = repository;
    }

    public List<MaquinasAgricolas> findAll() {
        return repository.findAll();
    }

    public List<MaquinasAgricolas> findByFazenda(Fazenda fazenda) {
        return repository.findByFazenda(fazenda);
    }

    public MaquinasAgricolas findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Máquina não encontrada"));
    }

    public MaquinasAgricolas save(MaquinasAgricolas maquina) {
        return repository.save(maquina);
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
