package com.api.dto.stock;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StockRequestDTO {
    @NotNull(message = "Quantidade de entrada estoque vazio")
    private Integer quantityInput;
    @NotNull(message = "Quantidade da saída esta vazia")
    private Integer quantityOutput;
    @NotNull(message = "Codigo do LOTE esta vazia")
    private long batchId;
    @NotNull(message = "Codigo do produto esta vazio")
    private long productId;
    private Integer discardQuantity;
    private String discardReason;
    private LocalDateTime createdAt;
}
