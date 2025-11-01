package com.api.dto.product;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

@Value
public class ProductResponseDTO {
    @Schema(description = "ID do produto", example = "1")
    private long id;
    @Schema(description = "Nome do produto", example = "Camiseta")
    private String name;
    @Schema(description = "Preço do produto", example = "49.90")
    private String brand;
    @Schema(description = "Categoria do produto", example = "Roupas")
    private String category;
    @Schema(description = "Descrição do produto", example = "Camiseta 100% algodão")
    private String description;
    @Schema(description = "ID da empresa do produto", example = "1")
    private long enterpriseId;
}
