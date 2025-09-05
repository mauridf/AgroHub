package com.agrohub.application;

import com.agrohub.domain.*;
import com.agrohub.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class InsumoService {

    private final InsumoRepository insumoRepository;
    private final EstoqueInsumoRepository estoqueRepository;
    private final CompraInsumoRepository compraRepository;

    public InsumoService(InsumoRepository insumoRepository,
                         EstoqueInsumoRepository estoqueRepository,
                         CompraInsumoRepository compraRepository) {
        this.insumoRepository = insumoRepository;
        this.estoqueRepository = estoqueRepository;
        this.compraRepository = compraRepository;
    }

    // CRUD Insumo
    public List<Insumo> findAllInsumos() {
        return insumoRepository.findAll();
    }

    public List<CompraInsumo> findAllCompras() {
        return compraRepository.findAll();
    }

    public Insumo findInsumoById(UUID id) {
        return insumoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Insumo não encontrado"));
    }

    public Insumo saveInsumo(Insumo insumo) {
        return insumoRepository.save(insumo);
    }

    public void deleteInsumo(UUID id) {
        insumoRepository.deleteById(id);
    }

    // CRUD Estoque
    public List<EstoqueInsumo> findEstoqueByFazenda(Fazenda fazenda) {
        return estoqueRepository.findByFazenda(fazenda);
    }

    // Compra de insumo com atualização de estoque
    @Transactional
    public CompraInsumo registrarCompra(CompraInsumo compra) {
        // Salva compra
        CompraInsumo savedCompra = compraRepository.save(compra);

        // Atualiza estoque
        EstoqueInsumo estoque = estoqueRepository.findByFazendaAndInsumo(compra.getFazenda(), compra.getInsumo())
                .orElse(new EstoqueInsumo(compra.getFazenda(), compra.getInsumo(), 0.0, null));

        estoque.setQuantidade(estoque.getQuantidade() + compra.getQuantidade());

        // Mantemos validade do estoque como a menor validade existente
        if (estoque.getValidade() == null || (compra.getDataCompra() != null && compra.getDataCompra().isBefore(estoque.getValidade()))) {
            estoque.setValidade(compra.getDataCompra());
        }

        estoqueRepository.save(estoque);
        return savedCompra;
    }
}
