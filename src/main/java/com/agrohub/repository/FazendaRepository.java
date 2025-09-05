package com.agrohub.repository;

import com.agrohub.domain.Fazenda;
import com.agrohub.domain.Produtor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface FazendaRepository extends JpaRepository<Fazenda, UUID> {
    List<Fazenda> findByProdutor(Produtor produtor);
}
