package com.api.dto.stock;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

import java.time.LocalDateTime;

@Value
public class StockResponseDTO {
    @Schema(description = "ID do registro de estoque", example = "1")
    long id;
    @Schema(description = "Quantidade de entrada no estoque", example = "100")
    Integer quantityInput;
    @Schema(description = "Quantidade de saída no estoque", example = "50")
    Integer quantityOutput;
    @Schema(description = "ID do lote associado ao estoque", example = "1")
    long batchId;
    @Schema(description = "ID do produto associado ao estoque", example = "1")
    long productId;
    @Schema(description = "Quantidade descartada no estoque", example = "10")
    Integer discardQuantity;
    @Schema(description = "Motivo do descarte", example = "Produto vencido")
    String discardReason;
    @Schema(description = "Data de criação do registro de estoque", example = "2023-10-01T10:00:00")
    LocalDateTime createdAt;
}
