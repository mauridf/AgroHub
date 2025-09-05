package com.agrohub.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "alerta_monitoramento")
public class AlertaMonitoramento {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "fazenda_id", nullable = false)
    private Fazenda fazenda;

    private String tipo; // praga, doença, clima, maquinário
    private String descricao;
    private String nivel; // baixo, médio, alto
    private LocalDateTime data;
    private Boolean resolvido;

    public AlertaMonitoramento() {}

    public AlertaMonitoramento(Fazenda fazenda, String tipo, String descricao, String nivel,
                               LocalDateTime data, Boolean resolvido) {
        this.fazenda = fazenda;
        this.tipo = tipo;
        this.descricao = descricao;
        this.nivel = nivel;
        this.data = data;
        this.resolvido = resolvido;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public Fazenda getFazenda() { return fazenda; }
    public void setFazenda(Fazenda fazenda) { this.fazenda = fazenda; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getNivel() { return nivel; }
    public void setNivel(String nivel) { this.nivel = nivel; }

    public LocalDateTime getData() { return data; }
    public void setData(LocalDateTime data) { this.data = data; }

    public Boolean getResolvido() { return resolvido; }
    public void setResolvido(Boolean resolvido) { this.resolvido = resolvido; }
}
