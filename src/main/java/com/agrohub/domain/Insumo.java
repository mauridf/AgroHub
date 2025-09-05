package com.agrohub.domain;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "insumos")
public class Insumo {

    @Id
    @GeneratedValue
    private UUID id;

    private String nome;
    private String tipo; // fertilizante, semente, defensivo, etc.
    private String unidadeMedida;
    private String fornecedor;

    public Insumo() {}

    public Insumo(String nome, String tipo, String unidadeMedida, String fornecedor) {
        this.nome = nome;
        this.tipo = tipo;
        this.unidadeMedida = unidadeMedida;
        this.fornecedor = fornecedor;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getUnidadeMedida() { return unidadeMedida; }
    public void setUnidadeMedida(String unidadeMedida) { this.unidadeMedida = unidadeMedida; }

    public String getFornecedor() { return fornecedor; }
    public void setFornecedor(String fornecedor) { this.fornecedor = fornecedor; }
}
