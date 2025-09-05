package com.agrohub.domain;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "maquinas_agricolas")
public class MaquinasAgricolas {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "fazenda_id", nullable = false)
    private Fazenda fazenda;

    private String descricao;
    private String marca;
    private String modelo;
    private Integer ano;
    private Double valorAproximado;

    public MaquinasAgricolas() {}

    public MaquinasAgricolas(Fazenda fazenda, String descricao, String marca, String modelo, Integer ano, Double valorAproximado) {
        this.fazenda = fazenda;
        this.descricao = descricao;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.valorAproximado = valorAproximado;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Fazenda getFazenda() {
        return fazenda;
    }

    public void setFazenda(Fazenda fazenda) {
        this.fazenda = fazenda;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Double getValorAproximado() {
        return valorAproximado;
    }

    public void setValorAproximado(Double valorAproximado) {
        this.valorAproximado = valorAproximado;
    }
}
