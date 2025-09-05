package com.agrohub.domain;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "custo_operacional")
public class CustoOperacional {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "fazenda_id", nullable = false)
    private Fazenda fazenda;

    private String descricao;
    private Double valor;
    private LocalDate data;

    public CustoOperacional() {}

    public CustoOperacional(Fazenda fazenda, String descricao, Double valor, LocalDate data) {
        this.fazenda = fazenda;
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public Fazenda getFazenda() { return fazenda; }
    public void setFazenda(Fazenda fazenda) { this.fazenda = fazenda; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Double getValor() { return valor; }
    public void setValor(Double valor) { this.valor = valor; }

    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }
}
