package com.api.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
@Entity
public class Vitrine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private long id;

    @Column(length = 500)
    private String descricao;

    @NotNull(message = "Preço vazio")
    private double preco;

    @NotNull(message = "lote_id vazio")
    private long lote_id;

    public Vitrine() {
    }

    public Vitrine(long id, String descricao, double preco, long lote_id) {
        this.id = id;
        this.descricao = descricao;
        this.preco = preco;
        this.lote_id = lote_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public long getLote_id() {
        return lote_id;
    }

    public void setLote_id(long lote_id) {
        this.lote_id = lote_id;
    }

    @Override
    public String toString() {
        return "Vitrine{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", preco=" + preco +
                ", lote_id=" + lote_id +
                '}';
    }
}
