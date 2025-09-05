package com.agrohub.domain;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "fazendas")
public class Fazenda {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "produtor_id", nullable = false)
    private Produtor produtor;

    @Column(nullable = false)
    private String nome;

    private String endereco;
    private String cidade;
    private String estado;

    private Double latitude;
    private Double longitude;

    @Column(nullable = false)
    private Double areaTotalHa;

    private Double areaAgricultavelHa;
    private Double areaVegetacaoHa;
    private Double areaConstruidaHa;

    private String inscricaoEstadual;
    private String codigoCar;
    private String ccir;
    private String fonteAgua;

    // Regra de negócio: soma das áreas não pode exceder a área total
    @PrePersist
    @PreUpdate
    private void validarAreas() {
        double soma = (areaAgricultavelHa != null ? areaAgricultavelHa : 0)
                + (areaVegetacaoHa != null ? areaVegetacaoHa : 0)
                + (areaConstruidaHa != null ? areaConstruidaHa : 0);
        if (soma > areaTotalHa) {
            throw new IllegalArgumentException("Soma das áreas não pode ser maior que a área total da fazenda.");
        }
    }

    public Fazenda() {}

    public Fazenda(UUID id, Produtor produtor, String nome, String endereco, String cidade,
                   String estado, Double latitude, Double longitude, Double areaTotalHa,
                   Double areaAgricultavelHa, Double areaVegetacaoHa, Double areaConstruidaHa,
                   String inscricaoEstadual, String codigoCar, String ccir, String fonteAgua) {
        this.id = id;
        this.produtor = produtor;
        this.nome = nome;
        this.endereco = endereco;
        this.cidade = cidade;
        this.estado = estado;
        this.latitude = latitude;
        this.longitude = longitude;
        this.areaTotalHa = areaTotalHa;
        this.areaAgricultavelHa = areaAgricultavelHa;
        this.areaVegetacaoHa = areaVegetacaoHa;
        this.areaConstruidaHa = areaConstruidaHa;
        this.inscricaoEstadual = inscricaoEstadual;
        this.codigoCar = codigoCar;
        this.ccir = ccir;
        this.fonteAgua = fonteAgua;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public Produtor getProdutor() { return produtor; }
    public void setProdutor(Produtor produtor) { this.produtor = produtor; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }

    public Double getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }

    public Double getAreaTotalHa() { return areaTotalHa; }
    public void setAreaTotalHa(Double areaTotalHa) { this.areaTotalHa = areaTotalHa; }

    public Double getAreaAgricultavelHa() { return areaAgricultavelHa; }
    public void setAreaAgricultavelHa(Double areaAgricultavelHa) { this.areaAgricultavelHa = areaAgricultavelHa; }

    public Double getAreaVegetacaoHa() { return areaVegetacaoHa; }
    public void setAreaVegetacaoHa(Double areaVegetacaoHa) { this.areaVegetacaoHa = areaVegetacaoHa; }

    public Double getAreaConstruidaHa() { return areaConstruidaHa; }
    public void setAreaConstruidaHa(Double areaConstruidaHa) { this.areaConstruidaHa = areaConstruidaHa; }

    public String getInscricaoEstadual() { return inscricaoEstadual; }
    public void setInscricaoEstadual(String inscricaoEstadual) { this.inscricaoEstadual = inscricaoEstadual; }

    public String getCodigoCar() { return codigoCar; }
    public void setCodigoCar(String codigoCar) { this.codigoCar = codigoCar; }

    public String getCcir() { return ccir; }
    public void setCcir(String ccir) { this.ccir = ccir; }

    public String getFonteAgua() { return fonteAgua; }
    public void setFonteAgua(String fonteAgua) { this.fonteAgua = fonteAgua; }
}

