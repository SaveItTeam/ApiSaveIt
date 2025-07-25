
package com.api.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "Campo vazio")
    @Column(unique = true)
    private long id;
    @Column(length = 100)
    private String nome;
    @Column(length = 50)
    private String estado;
    @Column(length = 100)
    private String cidade;
    @Column(length = 100)
    private String rua;
    @Column(length = 20)
    private String cep;
    @Column(length = 100)
    private String bairro;
    @Column(length = 100)
    private String complemento;

    public Endereco() {
    }

    public Endereco(long id, String nome, String estado, String rua, String cep, String bairro, String complemento) {
        this.id = id;
        this.nome = nome;
        this.estado = estado;
        this.rua = rua;
        this.cep = cep;
        this.bairro = bairro;
        this.complemento = complemento;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    @Override
    public String toString() {
        return "Endereco{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", estado='" + estado + '\'' +
                ", cidade='" + cidade + '\'' +
                ", rua='" + rua + '\'' +
                ", cep='" + cep + '\'' +
                ", bairro='" + bairro + '\'' +
                ", complemento='" + complemento + '\'' +
                '}';
    }
}