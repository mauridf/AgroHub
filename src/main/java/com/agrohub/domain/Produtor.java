package com.agrohub.domain;

import com.agrohub.domain.validation.CPFValido;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "produtores")
public class Produtor {

    @Id
    @GeneratedValue
    private UUID id;

    @OneToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(nullable = false)
    private String nome;

    @CPFValido
    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

    private String rg;
    private String inscricaoEstadual;
    private LocalDate dataNascimento;
    private String telefone;
    private String endereco;
    private String cidade;
    private String estado;
    private String dadosBancarios;
    private String car; // Cadastro Ambiental Rural

    public Produtor() {}

    public Produtor(UUID id, Usuario usuario, String nome, String cpf, String rg, String inscricaoEstadual,
                    LocalDate dataNascimento, String telefone, String endereco, String cidade,
                    String estado, String dadosBancarios, String car) {
        this.id = id;
        this.usuario = usuario;
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
        this.inscricaoEstadual = inscricaoEstadual;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.endereco = endereco;
        this.cidade = cidade;
        this.estado = estado;
        this.dadosBancarios = dadosBancarios;
        this.car = car;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getRg() { return rg; }
    public void setRg(String rg) { this.rg = rg; }

    public String getInscricaoEstadual() { return inscricaoEstadual; }
    public void setInscricaoEstadual(String inscricaoEstadual) { this.inscricaoEstadual = inscricaoEstadual; }

    public LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getDadosBancarios() { return dadosBancarios; }
    public void setDadosBancarios(String dadosBancarios) { this.dadosBancarios = dadosBancarios; }

    public String getCar() { return car; }
    public void setCar(String car) { this.car = car; }
}
