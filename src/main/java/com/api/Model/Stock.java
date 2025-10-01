package com.api.Model;

import jakarta.persistence.*;

@Entity
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private long id;
    private int quantity_input;
    private int quantity_output;
    private int batch_id;

    public Stock() {
    }

    public Stock(long id, int quantity_input, int quantity_output, int batch_id) {
        this.id = id;
        this.quantity_input = quantity_input;
        this.quantity_output = quantity_output;
        this.batch_id = batch_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getQuantity_input() {
        return quantity_input;
    }

    public void setQuantity_input(int quantity) {
        this.quantity_input = quantity;
    }

    public int getQuantity_output() {
        return quantity_output;
    }

    public void setQuantity_output(int quantity_output) {
        this.quantity_output = quantity_output;
    }

    public int getBatch_id() {
        return batch_id;
    }

    public void setBatch_id(int batch_id) {
        this.batch_id = batch_id;
    }

    @Override
    public String toString() {
        return "Estoque{" +
                "id=" + id +
                ", quantity_input=" + quantity_input +
                ", quantity_output=" + quantity_output +
                ", batch_id=" + batch_id +
                '}';
    }
}
