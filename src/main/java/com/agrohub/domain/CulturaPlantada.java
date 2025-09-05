package com.agrohub.domain;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "culturas_plantadas")
public class CulturaPlantada {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "fazenda_id", nullable = false)
    private Fazenda fazenda;

    @ManyToOne
    @JoinColumn(name = "cultura_id", nullable = false)
    private Cultura cultura;

    private String safra; // ex: 2024/2025
    private Double areaPlantadaHa;
    private LocalDate dataPlantio;
    private LocalDate dataColheitaPrevista;
    private LocalDate dataColheitaReal;
    private Double produtividadeEsperadaSacasHa;
    private Double produtividadeObtidaSacasHa;
    private Double custoTotal;
    private Double receitaTotal;

    public CulturaPlantada() {}

    public CulturaPlantada(UUID id, Fazenda fazenda, Cultura cultura, String safra,
                           Double areaPlantadaHa, LocalDate dataPlantio, LocalDate dataColheitaPrevista,
                           LocalDate dataColheitaReal, Double produtividadeEsperadaSacasHa,
                           Double produtividadeObtidaSacasHa, Double custoTotal, Double receitaTotal) {
        this.id = id;
        this.fazenda = fazenda;
        this.cultura = cultura;
        this.safra = safra;
        this.areaPlantadaHa = areaPlantadaHa;
        this.dataPlantio = dataPlantio;
        this.dataColheitaPrevista = dataColheitaPrevista;
        this.dataColheitaReal = dataColheitaReal;
        this.produtividadeEsperadaSacasHa = produtividadeEsperadaSacasHa;
        this.produtividadeObtidaSacasHa = produtividadeObtidaSacasHa;
        this.custoTotal = custoTotal;
        this.receitaTotal = receitaTotal;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public Fazenda getFazenda() { return fazenda; }
    public void setFazenda(Fazenda fazenda) { this.fazenda = fazenda; }

    public Cultura getCultura() { return cultura; }
    public void setCultura(Cultura cultura) { this.cultura = cultura; }

    public String getSafra() { return safra; }
    public void setSafra(String safra) { this.safra = safra; }

    public Double getAreaPlantadaHa() { return areaPlantadaHa; }
    public void setAreaPlantadaHa(Double areaPlantadaHa) { this.areaPlantadaHa = areaPlantadaHa; }

    public LocalDate getDataPlantio() { return dataPlantio; }
    public void setDataPlantio(LocalDate dataPlantio) { this.dataPlantio = dataPlantio; }

    public LocalDate getDataColheitaPrevista() { return dataColheitaPrevista; }
    public void setDataColheitaPrevista(LocalDate dataColheitaPrevista) { this.dataColheitaPrevista = dataColheitaPrevista; }

    public LocalDate getDataColheitaReal() { return dataColheitaReal; }
    public void setDataColheitaReal(LocalDate dataColheitaReal) { this.dataColheitaReal = dataColheitaReal; }

    public Double getProdutividadeEsperadaSacasHa() { return produtividadeEsperadaSacasHa; }
    public void setProdutividadeEsperadaSacasHa(Double produtividadeEsperadaSacasHa) { this.produtividadeEsperadaSacasHa = produtividadeEsperadaSacasHa; }

    public Double getProdutividadeObtidaSacasHa() { return produtividadeObtidaSacasHa; }
    public void setProdutividadeObtidaSacasHa(Double produtividadeObtidaSacasHa) { this.produtividadeObtidaSacasHa = produtividadeObtidaSacasHa; }

    public Double getCustoTotal() { return custoTotal; }
    public void setCustoTotal(Double custoTotal) { this.custoTotal = custoTotal; }

    public Double getReceitaTotal() { return receitaTotal; }
    public void setReceitaTotal(Double receitaTotal) { this.receitaTotal = receitaTotal; }
}

