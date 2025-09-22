package com.api.dto.Batch;

import java.util.Date;

public class BatchListDTO {
    private Long id;
    private String name;
    private Date expiration_date;
    private int quantity;
    private String image;

    public BatchListDTO(Long id, String name, Date expiration_date, int quantity, String image) {
        this.id = id;
        this.name = name;
        this.expiration_date = expiration_date;
        this.quantity = quantity;
        this.image = image;
    }

    public BatchListDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getExpiration_date() {
        return expiration_date;
    }

    public void setExpiration_date(Date expiration_date) {
        this.expiration_date = expiration_date;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "BatchListDTO{" +
                "enterpriseId=" + id +
                ", name='" + name + '\'' +
                ", expirationDate=" + expiration_date +
                ", quantity=" + quantity +
                ", imageUrl='" + image + '\'' +
                '}';
    }
}
