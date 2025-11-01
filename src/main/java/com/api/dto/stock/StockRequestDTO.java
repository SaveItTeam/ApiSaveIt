package com.api.dto.stock;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StockRequestDTO {
    @NotNull(message = "Quantidade de entrada estoque vazio")
    @Schema(description = "Quantidade de entrada no estoque", example = "100")
    private Integer quantityInput;
    @NotNull(message = "Quantidade da saída esta vazia")
    @Schema(description = "Quantidade de saída no estoque", example = "50")
    private Integer quantityOutput;
    @NotNull(message = "Codigo do LOTE esta vazia")
    @Schema(description = "ID do lote associado ao estoque", example = "1")
    private long batchId;
    @NotNull(message = "Codigo do produto esta vazio")
    @Schema(description = "ID do produto associado ao estoque", example = "1")
    private long productId;
    @Schema(description = "Quantidade descartada no estoque", example = "10")
    private Integer discardQuantity;
    @Schema(description = "Motivo do descarte", example = "Produto vencido")
    private String discardReason;
    @Schema(description = "Data de criação do registro de estoque", example = "2023-10-01T10:00:00")
    private LocalDateTime createdAt;
}
