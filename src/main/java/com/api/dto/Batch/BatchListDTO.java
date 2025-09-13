package com.api.dto.Batch;

import java.util.Date;

public class BatchListDTO {
    private int enterpriseId;
    private String name;
    private Date expirationDate;
    private int quantity;
    private String imageUrl;

    public BatchListDTO(int enterpriseId, String name, Date expirationDate, int quantity, String imageUrl) {
        this.enterpriseId = enterpriseId;
        this.name = name;
        this.expirationDate = expirationDate;
        this.quantity = quantity;
        this.imageUrl = imageUrl;
    }


    public int getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(int enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "BatchListDTO{" +
                "enterpriseId=" + enterpriseId +
                ", name='" + name + '\'' +
                ", expirationDate=" + expirationDate +
                ", quantity=" + quantity +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
