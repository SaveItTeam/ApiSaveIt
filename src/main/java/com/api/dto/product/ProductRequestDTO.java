package com.api.dto.product;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductRequestDTO {
    @NotNull(message = "Nome vazio")
    @Column(length = 100)
    private String name;
    @NotNull(message = "Marca vazio")
    @Column(length = 50)
    private String brand;
    @Column(length = 50)
    @NotNull(message = "Categoria vazia")
    private String category;
    @Column(length = 500)
    private String description;
    @NotNull(message = "Id da empresa vazia")
    private Long enterpriseId;
}
