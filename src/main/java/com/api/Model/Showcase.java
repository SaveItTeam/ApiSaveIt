package com.api.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
@Entity
public class Showcase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private long id;
    private String description;
    private double price;
    private long batch_id;

    public Showcase() {
    }

    public Showcase(long id, String description, double price, long batch_id) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.batch_id = batch_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getBatch_id() {
        return batch_id;
    }

    public void setBatch_id(long batch_id) {
        this.batch_id = batch_id;
    }

    @Override
    public String toString() {
        return "Vitrine{" +
                "id=" + id +
                ", descricao='" + description + '\'' +
                ", preco=" + price +
                ", lote_id=" + batch_id +
                '}';
    }
}
