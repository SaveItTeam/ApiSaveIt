package com.api.dto.Batch;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Value;

import java.util.Date;

@Value
public class BatchResponseDTO {
    @Schema(description = "ID do lote", example = "1")
    private long id;
    @Schema(description = "Unidade de medida do lote", example = "kg, g, l, ml, un")
    private String unit_measure;
    @NotNull(message = "Data de entrada vazia vazio")
    private Date entry_date;
    @Schema(description = "Código do lote", example = "ABC123")
    private String batch_code;
    @Schema(description = "Data de validade do lote", example = "2024-10-10")
    private Date expiration_date;
    @Schema(description = "Quantidade de itens no lote", example = "100")
    private int quantity_measure;
    @Schema(description = "ID do produto associado ao lote", example = "1")
    private long product_id;
}
