package com.api.dto.Plan;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;

public class PlanRequestDTO {
    @NotNull(message = "Campo vazio")
    @Column(unique = true)
    private long id;
    @NotNull(message = "Nome vazio")
    @Column(length = 255)
    private String name;
    @NotNull(message = "Plano vazio")
    private Double price;
    @Column(length = 255)
    @NotNull(message = "Descricao vazio")
    private String description;

    public PlanRequestDTO(String description, Double price, String name, long id) {
        this.description = description;
        this.price = price;
        this.name = name;
        this.id = id;
    }

    public PlanRequestDTO() {
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
