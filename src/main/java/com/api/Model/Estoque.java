package com.api.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Estoque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "Campo vazio")
    @Column(unique = true)
    private long id;
    @NotNull(message = "Quantidade de estoque vazio")
    private int quantidade;
    @NotNull(message = "Quantidade da saída esta vazia")
    private int quantidade_saida;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getQuantidade_saida() {
        return quantidade_saida;
    }

    public void setQuantidade_saida(int quantidade_saida) {
        this.quantidade_saida = quantidade_saida;
    }

    public Estoque() {
    }

    public Estoque(long id, int quantidade, int quantidade_saida) {
        this.id = id;
        this.quantidade = quantidade;
        this.quantidade_saida = quantidade_saida;
    }

    @Override
    public String toString() {
        return "Estoque{" +
                "id=" + id +
                ", quantidade=" + quantidade +
                ", quantidade_saida=" + quantidade_saida +
                '}';
    }
}
