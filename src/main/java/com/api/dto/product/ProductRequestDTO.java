package com.api.dto.product;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductRequestDTO {
    @NotNull(message = "Nome vazio")
    @Column(length = 100)
    @Schema(description = "Nome do produto", example = "Camiseta")
    private String name;
    @NotNull(message = "Marca vazio")
    @Column(length = 50)
    @Schema(description = "Marca do produto", example = "Nike")
    private String brand;
    @Column(length = 50)
    @NotNull(message = "Categoria vazia")
    @Schema(description = "Categoria do produto", example = "Roupas")
    private String category;
    @Column(length = 500)
    @Schema(description = "Descrição do produto", example = "Camiseta 100% algodão")
    private String description;
    @Schema(description = "ID da empresa do produto", example = "1")
    @NotNull(message = "Id da empresa vazia")
    private Long enterpriseId;
}
