package com.api.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String brand;
    private long enterprise_id;

    public Product() {
    }

    public Product(long id, String name, String brand,long enterprise_id) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.enterprise_id = enterprise_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public long getEnterprise_id() {
        return enterprise_id;
    }

    public void setEnterprise_id(long enterprise_id) {
        this.enterprise_id = enterprise_id;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", sku='" + sku + '\'' +
                ", nome='" + name + '\'' +
                ", marca='" + brand + '\'' +
                ", descricao='" + descricao + '\'' +
                ", empresa_id=" + enterprise_id +
                '}';
    }

}
