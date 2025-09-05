package com.agrohub.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senhaHash;

    @Column(nullable = false)
    private String role; // ADM ou PROD

    @Column(nullable = false)
    private LocalDateTime dataCadastro;

    private LocalDateTime ultimoLogin;

    public Usuario() {}

    public Usuario(UUID id, String email, String senhaHash, String role,
                   LocalDateTime dataCadastro, LocalDateTime ultimoLogin) {
        this.id = id;
        this.email = email;
        this.senhaHash = senhaHash;
        this.role = role;
        this.dataCadastro = dataCadastro;
        this.ultimoLogin = ultimoLogin;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSenhaHash() { return senhaHash; }
    public void setSenhaHash(String senhaHash) { this.senhaHash = senhaHash; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public LocalDateTime getDataCadastro() { return dataCadastro; }
    public void setDataCadastro(LocalDateTime dataCadastro) { this.dataCadastro = dataCadastro; }

    public LocalDateTime getUltimoLogin() { return ultimoLogin; }
    public void setUltimoLogin(LocalDateTime ultimoLogin) { this.ultimoLogin = ultimoLogin; }
}
