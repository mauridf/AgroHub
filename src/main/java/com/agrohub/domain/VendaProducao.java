package com.agrohub.domain;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "venda_producao")
public class VendaProducao {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "cultura_plantada_id", nullable = false)
    private CulturaPlantada culturaPlantada;

    private Double quantidadeVendida;
    private Double precoUnitario;
    private LocalDate dataVenda;
    private String destino;

    public VendaProducao() {}

    public VendaProducao(CulturaPlantada culturaPlantada, Double quantidadeVendida, Double precoUnitario,
                         LocalDate dataVenda, String destino) {
        this.culturaPlantada = culturaPlantada;
        this.quantidadeVendida = quantidadeVendida;
        this.precoUnitario = precoUnitario;
        this.dataVenda = dataVenda;
        this.destino = destino;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public CulturaPlantada getCulturaPlantada() { return culturaPlantada; }
    public void setCulturaPlantada(CulturaPlantada culturaPlantada) { this.culturaPlantada = culturaPlantada; }

    public Double getQuantidadeVendida() { return quantidadeVendida; }
    public void setQuantidadeVendida(Double quantidadeVendida) { this.quantidadeVendida = quantidadeVendida; }

    public Double getPrecoUnitario() { return precoUnitario; }
    public void setPrecoUnitario(Double precoUnitario) { this.precoUnitario = precoUnitario; }

    public LocalDate getDataVenda() { return dataVenda; }
    public void setDataVenda(LocalDate dataVenda) { this.dataVenda = dataVenda; }

    public String getDestino() { return destino; }
    public void setDestino(String destino) { this.destino = destino; }
}
