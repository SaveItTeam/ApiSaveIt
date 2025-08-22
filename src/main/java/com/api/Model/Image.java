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
    @NotNull(message = "Codigo binario vazia")
    private Byte[] image;
    @NotNull(message = "id do produto vazio")
    private long product_id;


    public Image(long id, Byte[] image, long product_id) {
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

    public Byte[] getImage() {
        return image;
    }

    public void setImage(Byte[] image) {
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
                ", nome=" + Arrays.toString(image) +
                ", product_id=" + product_id +
                '}';
    }
}
