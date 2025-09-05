package com.agrohub.domain;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "contratos")
public class Contrato {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "fazenda_id", nullable = false)
    private Fazenda fazenda;

    private String tipo; // Arrendamento, Parceria, Financiamento
    private String parteContratante;
    private Double valor;
    private LocalDate dataInicio;
    private LocalDate dataFim; // nullable

    public Contrato() {}

    public Contrato(Fazenda fazenda, String tipo, String parteContratante, Double valor, LocalDate dataInicio, LocalDate dataFim) {
        this.fazenda = fazenda;
        this.tipo = tipo;
        this.parteContratante = parteContratante;
        this.valor = valor;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public Fazenda getFazenda() { return fazenda; }
    public void setFazenda(Fazenda fazenda) { this.fazenda = fazenda; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getParteContratante() { return parteContratante; }
    public void setParteContratante(String parteContratante) { this.parteContratante = parteContratante; }

    public Double getValor() { return valor; }
    public void setValor(Double valor) { this.valor = valor; }

    public LocalDate getDataInicio() { return dataInicio; }
    public void setDataInicio(LocalDate dataInicio) { this.dataInicio = dataInicio; }

    public LocalDate getDataFim() { return dataFim; }
    public void setDataFim(LocalDate dataFim) { this.dataFim = dataFim; }
}
