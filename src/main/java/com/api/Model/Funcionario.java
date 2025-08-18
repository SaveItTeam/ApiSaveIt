package com.api.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
@Entity
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private long id;
    @NotNull(message = "Nome vazio")
    @Column(length = 255)
    private String nome;
    @Column(length = 255)
    @NotNull(message = "Email vazio")
    private String email;
    @NotNull(message = "Senha vazia")
    @Column(length = 255)
    private String senha;
    @NotNull(message = "empresa_id vazio")
    private long empresa_id;

    public Funcionario() {
    }

    public Funcionario(long id, String nome, String email, String senha, long empresa_id) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.empresa_id = empresa_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public long getEmpresa_id() {
        return empresa_id;
    }

    public void setEmpresa_id(long empresa_id) {
        this.empresa_id = empresa_id;
    }


    @Override
    public String toString() {
        return "Funcionario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", empresa_id=" + empresa_id +
                '}';
    }
}
