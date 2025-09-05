package com.agrohub.domain;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "culturas")
public class Cultura {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String nome;

    private String ciclo; // anual, perene
    private String variedade;

    public Cultura() {}

    public Cultura(UUID id, String nome, String ciclo, String variedade) {
        this.id = id;
        this.nome = nome;
        this.ciclo = ciclo;
        this.variedade = variedade;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCiclo() { return ciclo; }
    public void setCiclo(String ciclo) { this.ciclo = ciclo; }

    public String getVariedade() { return variedade; }
    public void setVariedade(String variedade) { this.variedade = variedade; }
}

