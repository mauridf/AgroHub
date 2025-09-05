package com.agrohub.application;

import com.agrohub.domain.Fazenda;
import com.agrohub.domain.Produtor;
import com.agrohub.domain.Usuario;
import com.agrohub.repository.FazendaRepository;
import com.agrohub.repository.ProdutorRepository;
import org.springframework.stereotype.Service;
import com.agrohub.domain.validation.CPFValidator;

import java.util.List;
import java.util.UUID;

@Service
public class ProdutorService {

    private final ProdutorRepository produtorRepository;
    private final FazendaRepository fazendaRepository;

    public ProdutorService(ProdutorRepository produtorRepository,
                           FazendaRepository fazendaRepository) {
        this.produtorRepository = produtorRepository;
        this.fazendaRepository = fazendaRepository;
    }

    // Validação de CPF
    private void validarCPF(String cpf) {
        if (!CPFValidator.isValido(cpf)) {
            throw new RuntimeException("CPF inválido");
        }
    }

    // CRUD Produtor
    public List<Produtor> findAll() {
        return produtorRepository.findAll();
    }

    public Produtor findById(UUID id) {
        return produtorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produtor não encontrado"));
    }

    public Produtor saveProdutor(Produtor produtor) {
        validarCPF(produtor.getCpf());
        return produtorRepository.save(produtor);
    }

    public void deleteProdutor(UUID id) {
        produtorRepository.deleteById(id);
    }

    // CRUD Fazenda
    public Fazenda saveFazenda(Fazenda fazenda) {
        double somaAreas = (fazenda.getAreaAgricultavelHa() != null ? fazenda.getAreaAgricultavelHa() : 0) +
                (fazenda.getAreaConstruidaHa() != null ? fazenda.getAreaConstruidaHa() : 0) +
                (fazenda.getAreaVegetacaoHa() != null ? fazenda.getAreaVegetacaoHa() : 0);

        if (fazenda.getAreaTotalHa() < somaAreas) {
            throw new RuntimeException("A soma das áreas (agricultável + construída + vegetação) não pode ser maior que a área total da fazenda");
        }

        return fazendaRepository.save(fazenda);
    }

    public List<Fazenda> findFazendasByProdutor(Produtor produtor) {
        return fazendaRepository.findByProdutor(produtor);
    }

    public List<Fazenda> findAllFazendas() {
        return fazendaRepository.findAll();
    }

    public Fazenda findFazendaById(UUID id) {
        return fazendaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fazenda não encontrada"));
    }

    public void deleteFazenda(UUID id) {
        fazendaRepository.deleteById(id);
    }
}

