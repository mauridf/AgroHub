package com.agrohub.application;

import com.agrohub.domain.Certificacao;
import com.agrohub.domain.Fazenda;
import com.agrohub.repository.CertificacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CertificacaoService {

    private final CertificacaoRepository repository;

    public CertificacaoService(CertificacaoRepository repository) {
        this.repository = repository;
    }

    public List<Certificacao> findAll() {
        return repository.findAll();
    }

    public List<Certificacao> findByFazenda(Fazenda fazenda) {
        return repository.findByFazenda(fazenda);
    }

    public Certificacao findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Certificação não encontrada"));
    }

    public Certificacao save(Certificacao cert) {
        return repository.save(cert);
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
