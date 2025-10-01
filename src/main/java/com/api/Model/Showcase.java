package com.api.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Entity
public class Showcase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private long id;
    private String description;
    private double price;
    private long batch_id;
    private Date entrance_date;
    public Showcase() {
    }

    public Showcase(long id, String description, double price, long batch_id, Date entrance_date) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.batch_id = batch_id;
        this.entrance_date = entrance_date;
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
    public Date getEntrance_date() {
        return entrance_date;
    }
    public void setEntrance_date(Date entrance_date) {
        this.entrance_date = entrance_date;
    }
    @Override
    public String toString() {
        return "Vitrine{" +
                "id=" + id +
                ", descricao='" + description + '\'' +
                ", preco=" + price +
                ", lote_id=" + batch_id +
                ", data_entrada=" + entrance_date +
                '}';
    }
}
