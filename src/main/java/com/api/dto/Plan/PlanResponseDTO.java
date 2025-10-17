package com.api.dto.Plan;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

@Value
public class PlanResponseDTO {
    @Schema(description = "ID do plano", example = "1")
    private long id;
    @Schema(description = "Nome do plano", example = "Plano Básico")
    private String name;
    @Schema(description = "Preço do plano", example = "29.99")
    private Double price;
    @Schema(description = "Descrição do plano", example = "Acesso a funcionalidades básicas")
    private String description;
}
