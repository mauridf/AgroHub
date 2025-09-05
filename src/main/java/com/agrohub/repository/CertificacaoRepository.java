package com.agrohub.repository;

import com.agrohub.domain.Certificacao;
import com.agrohub.domain.Fazenda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CertificacaoRepository extends JpaRepository<Certificacao, UUID> {
    List<Certificacao> findByFazenda(Fazenda fazenda);
}
