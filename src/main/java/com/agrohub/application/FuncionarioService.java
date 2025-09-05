package com.agrohub.application;

import com.agrohub.domain.Funcionario;
import com.agrohub.domain.Fazenda;
import com.agrohub.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FuncionarioService {

    private final FuncionarioRepository repository;

    public FuncionarioService(FuncionarioRepository repository) {
        this.repository = repository;
    }

    public List<Funcionario> findAll() {
        return repository.findAll();
    }

    public List<Funcionario> findByFazenda(Fazenda fazenda) {
        return repository.findByFazenda(fazenda);
    }

    public Funcionario findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));
    }

    public Funcionario save(Funcionario funcionario) {
        // Aqui podemos adicionar validações futuras, ex: CPF único por fazenda
        return repository.save(funcionario);
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
