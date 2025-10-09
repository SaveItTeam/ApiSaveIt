package com.api.dto.Plan;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PlanRequestDTO {
    @NotNull(message = "Nome vazio")
    @Column(length = 255)
    @Schema(description = "Nome do plano", example = "Plano Básico")
    private String name;
    @Schema(description = "Preço do plano", example = "29.99")
    @NotNull(message = "Plano vazio")
    private Double price;
    @Schema(description = "Descrição do plano", example = "Acesso a funcionalidades básicas")
    @Column(length = 255)
    @NotNull(message = "Descricao vazio")
    private String description;
}
