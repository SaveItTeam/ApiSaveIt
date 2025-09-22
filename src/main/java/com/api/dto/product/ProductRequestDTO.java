package com.api.dto.product;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;

public class ProductRequestDTO {
    @Column(unique = true)
    private long id;
    @Column(length = 50)
    private String sku;
    @NotNull(message = "Nome vazio")
    @Column(length = 100)
    private String name;
    @NotNull(message = "Marca vazio")
    @Column(length = 50)
    private String brand;
    @Column(length = 500)
    private String descricao;
    @NotNull(message = "Id da empresa vazia")
    private long enterprise_id;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public long getEnterprise_id() {
        return enterprise_id;
    }

    public void setEnterprise_id(long enterprise_id) {
        this.enterprise_id = enterprise_id;
    }


}
