package com.agrohub.domain;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "certificacoes")
public class Certificacao {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "fazenda_id", nullable = false)
    private Fazenda fazenda;

    private String tipo; // Orgânico, FairTrade, etc.
    private LocalDate dataEmissao;
    private LocalDate dataValidade;

    public Certificacao() {}

    public Certificacao(Fazenda fazenda, String tipo, LocalDate dataEmissao, LocalDate dataValidade) {
        this.fazenda = fazenda;
        this.tipo = tipo;
        this.dataEmissao = dataEmissao;
        this.dataValidade = dataValidade;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public Fazenda getFazenda() { return fazenda; }
    public void setFazenda(Fazenda fazenda) { this.fazenda = fazenda; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public LocalDate getDataEmissao() { return dataEmissao; }
    public void setDataEmissao(LocalDate dataEmissao) { this.dataEmissao = dataEmissao; }

    public LocalDate getDataValidade() { return dataValidade; }
    public void setDataValidade(LocalDate dataValidade) { this.dataValidade = dataValidade; }
}
