package com.api.dto.Plan;

import io.swagger.v3.oas.annotations.media.Schema;

public class PlanResponseDTO {
    @Schema(description = "ID do plano", example = "1")
    private long id;
    @Schema(description = "Nome do plano", example = "Plano Básico")
    private String name;
    @Schema(description = "Preço do plano", example = "29.99")
    private Double price;
    @Schema(description = "Descrição do plano", example = "Acesso a funcionalidades básicas")
    private String description;

    public PlanResponseDTO(long id, String name, Double price, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
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

    @Override
    public String toString() {
        return "PlanResponseDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}
