package com.api.dto.batch;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.util.Date;

@Value
public class BatchResponseDTO {
    @Schema(description = "ID do lote", example = "1")
    private long id;
    @Schema(description = "Unidade de medida do lote", example = "kg, g, l, ml, un")
    private String unitMeasure;
    @NotNull(message = "Data de entrada vazia vazio")
    private Date entryDate;
    @Schema(description = "Código do lote", example = "ABC123")
    private String batchCode;
    @Schema(description = "Data de validade do lote", example = "2024-10-10")
    private Date expirationDate;
    @Schema(description = "Quantidade máxima de itens no lote", example = "500")
    private Integer maxQuantity;
    @Schema(description = "Quantidade de itens no lote", example = "100")
    private Integer quantity;
    @Schema(description = "ID do produto associado ao lote", example = "1")
    private long productId;
}
