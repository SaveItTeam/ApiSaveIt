package com.api.dto.batch;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BatchResponseDTO {
    @Schema(description = "ID do lote", example = "1")
    private long id;

    @Schema(description = "Unidade de medida do lote", example = "KG, L")
    private String unitMeasure;

    @Schema(description = "Data de entrada do lote", example = "2023-10-10")
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
    private Long productId;

    public BatchResponseDTO(com.api.model.Batch batch) {
        this.id = batch.getId();
        this.unitMeasure = batch.getUnitMeasure();
        this.entryDate = batch.getEntryDate();
        this.batchCode = batch.getBatchCode();
        this.expirationDate = batch.getExpirationDate();
        this.maxQuantity = batch.getMaxQuantity();
        this.quantity = batch.getQuantity();
        this.productId = (batch.getProduct() != null) ? batch.getProduct().getId() : null;
    }
}
