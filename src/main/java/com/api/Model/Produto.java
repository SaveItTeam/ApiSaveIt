package com.api.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "Campo vazio")
    @Column(unique = true)
    private long id;
    @Column(length = 50)
    private String sku;
    @NotNull(message = "Nome vazio")
    @Column(length = 100)
    private String nome;
    @NotNull(message = "Marca vazio")
    @Column(length = 50)
    private String marca;
    @Column(length = 500)//PErguntar pro povo de dados o tamanho da descricao
    private String descricao;
    @NotNull(message = "Data de validade nao pode ser nula")
    private Date dt_validade;
    @NotNull(message = "Data de fabricação nao pode ser nula")
    private Date dt_fabricacao;
    @NotNull(message = "Categoria vazio")
    @Column(length = 50)
    private String categoria;

    public Produto() {
    }

    public Produto(long id, String sku, String nome, String marca, String descricao, Date dt_validade, Date dt_fabricacao, String categoria) {
        this.id = id;
        this.sku = sku;
        this.nome = nome;
        this.marca = marca;
        this.descricao = descricao;
        this.dt_validade = dt_validade;
        this.dt_fabricacao = dt_fabricacao;
        this.categoria = categoria;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDt_validade() {
        return dt_validade;
    }

    public void setDt_validade(Date dt_validade) {
        this.dt_validade = dt_validade;
    }

    public Date getDt_fabricacao() {
        return dt_fabricacao;
    }

    public void setDt_fabricacao(Date dt_fabricacao) {
        this.dt_fabricacao = dt_fabricacao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", sku='" + sku + '\'' +
                ", nome='" + nome + '\'' +
                ", marca='" + marca + '\'' +
                ", descricao='" + descricao + '\'' +
                ", dt_validade=" + dt_validade +
                ", dt_fabricacao=" + dt_fabricacao +
                ", categoria='" + categoria + '\'' +
                '}';
    }

}
