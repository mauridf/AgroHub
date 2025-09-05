package com.agrohub.application;

import com.agrohub.domain.CulturaPlantada;
import com.agrohub.domain.VendaProducao;
import com.agrohub.repository.VendaProducaoRepository;
import com.agrohub.repository.CulturaPlantadaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class VendaProducaoService {

    private final VendaProducaoRepository repository;
    private final CulturaPlantadaRepository culturaPlantadaRepository;

    public VendaProducaoService(VendaProducaoRepository repository,
                                CulturaPlantadaRepository culturaPlantadaRepository) {
        this.repository = repository;
        this.culturaPlantadaRepository = culturaPlantadaRepository;
    }

    public List<VendaProducao> findAll() {
        return repository.findAll();
    }

    public VendaProducao findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venda não encontrada"));
    }

    @Transactional
    public VendaProducao save(VendaProducao venda) {
        VendaProducao savedVenda = repository.save(venda);

        // Atualiza receita total da CulturaPlantada
        CulturaPlantada cp = savedVenda.getCulturaPlantada();
        double receitaAtual = cp.getReceitaTotal() != null ? cp.getReceitaTotal() : 0;
        receitaAtual += savedVenda.getQuantidadeVendida() * savedVenda.getPrecoUnitario();
        cp.setReceitaTotal(receitaAtual);

        culturaPlantadaRepository.save(cp);
        return savedVenda;
    }

    public void delete(UUID id) {
        VendaProducao venda = findById(id);
        CulturaPlantada cp = venda.getCulturaPlantada();

        // Subtrai a receita
        double receitaAtual = cp.getReceitaTotal() != null ? cp.getReceitaTotal() : 0;
        receitaAtual -= venda.getQuantidadeVendida() * venda.getPrecoUnitario();
        cp.setReceitaTotal(receitaAtual);
        culturaPlantadaRepository.save(cp);

        repository.deleteById(id);
    }

    public List<VendaProducao> findByCulturaPlantada(CulturaPlantada culturaPlantada) {
        return repository.findByCulturaPlantada(culturaPlantada);
    }
}
