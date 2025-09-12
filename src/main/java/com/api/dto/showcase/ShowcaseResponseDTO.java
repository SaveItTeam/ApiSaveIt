package com.api.dto.showcase;

public class ShowcaseResponseDTO {
    private long id;
    private String description;
    private double price;
    private long batch_id;

    public ShowcaseResponseDTO(long id, String description, double price, long batch_id) {
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
}
