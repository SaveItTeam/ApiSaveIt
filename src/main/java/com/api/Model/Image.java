package com.api.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Arrays;

@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private long id;
    private String image;
    private long product_id;


    public Image(long id, String image, long product_id) {
        this.id = id;
        this.image = image;
        this.product_id = product_id;
    }

    public Image() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(long product_id) {
        this.product_id = product_id;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", nome=" + image +
                ", product_id=" + product_id +
                '}';
    }
}
