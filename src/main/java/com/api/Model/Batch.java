package com.api.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Entity
public class Batch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "Campo vazio")
    @Column(unique = true)
    private long id;
    private String unite_measure;
    private Date entry_date;
    private String batch_code;
    private Date expiriation_date;
    private int quantity;
    private long product_id;

    public Batch(long id, String unite_measure, Date entry_date, String batch_code, Date expiriation_date, int quantity, long product_id) {
        this.id = id;
        this.unite_measure = unite_measure;
        this.entry_date = entry_date;
        this.batch_code = batch_code;
        this.expiriation_date = expiriation_date;
        this.quantity = quantity;
        this.product_id = product_id;
    }

    public Batch() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUnite_measure() {
        return unite_measure;
    }

    public void setUnite_measure(String unite_measure) {
        this.unite_measure = unite_measure;
    }

    public Date getEntry_date() {
        return entry_date;
    }

    public void setEntry_date(Date entry_date) {
        this.entry_date = entry_date;
    }

    public String getBatch_code() {
        return batch_code;
    }

    public void setBatch_code(String batch_code) {
        this.batch_code = batch_code;
    }

    public Date getExpiriation_date() {
        return expiriation_date;
    }

    public void setExpiriation_date(Date expiriation_date) {
        this.expiriation_date = expiriation_date;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(long product_id) {
        this.product_id = product_id;
    }

    @Override
    public String toString() {
        return "Lote{" +
                "id=" + id +
                ", unite_measure='" + unite_measure + '\'' +
                ", entry_date=" + entry_date +
                ", batch_code='" + batch_code + '\'' +
                ", expiriation_date=" + expiriation_date +
                ", quantity=" + quantity +
                ", product_id=" + product_id +
                '}';
    }
}
