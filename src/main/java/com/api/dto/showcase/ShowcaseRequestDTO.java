package com.api.dto.showcase;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;

public class ShowcaseRequestDTO {
    @Column(unique = true)
    private long id;

    @Column(length = 500)
    private String description;

    @NotNull(message = "Preço vazio")
    private double price;

    @NotNull(message = "lote_id vazio")
    private long batch_id;

    public ShowcaseRequestDTO() {
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
}
