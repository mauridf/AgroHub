package com.agrohub.domain;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "clima")
public class Clima {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "fazenda_id", nullable = false)
    private Fazenda fazenda;

    private LocalDate data;
    private Double temperatura;
    private Double chuvaMm;
    private Double umidade;

    public Clima() {}

    public Clima(Fazenda fazenda, LocalDate data, Double temperatura, Double chuvaMm, Double umidade) {
        this.fazenda = fazenda;
        this.data = data;
        this.temperatura = temperatura;
        this.chuvaMm = chuvaMm;
        this.umidade = umidade;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public Fazenda getFazenda() { return fazenda; }
    public void setFazenda(Fazenda fazenda) { this.fazenda = fazenda; }

    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }

    public Double getTemperatura() { return temperatura; }
    public void setTemperatura(Double temperatura) { this.temperatura = temperatura; }

    public Double getChuvaMm() { return chuvaMm; }
    public void setChuvaMm(Double chuvaMm) { this.chuvaMm = chuvaMm; }

    public Double getUmidade() { return umidade; }
    public void setUmidade(Double umidade) { this.umidade = umidade; }
}
