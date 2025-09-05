package com.agrohub.repository;

import com.agrohub.domain.Funcionario;
import com.agrohub.domain.Fazenda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface FuncionarioRepository extends JpaRepository<Funcionario, UUID> {
    List<Funcionario> findByFazenda(Fazenda fazenda);
}
