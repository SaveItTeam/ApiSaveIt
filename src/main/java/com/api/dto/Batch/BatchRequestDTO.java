package com.api.dto.Batch;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class BatchRequestDTO {
    @Column(length = 255)
    @NotNull(message = "Unidade de medida vazio")
    @Schema(description = "Unidade de medida do lote", example = "kg, g, l, ml, un")
    private String unit_measure;
    @Schema(description = "Data de entrada do lote", example = "2023-10-10")
    @NotNull(message = "Data de entrada vazia vazio")
    private Date entry_date;
    @Column(length = 255)
    @NotNull(message = "Batch_code vazio")
    @Schema(description = "Código do lote", example = "ABC123")
    private String batch_code;
    @NotNull(message = "Data de validade vazio")
    @JsonProperty("expiration_date")
    @Schema(description = "Data de validade do lote", example = "2024-10-10")
    private Date expiration_date;
    @Column(length = 50)
    @NotNull(message = "Quantidade vazio")
    @Schema(description = "Quantidade de itens no lote", example = "100")
    private int quantity_measure;
    @Column(length = 20)
    @NotNull(message = "Id do produto vazio")
    @Schema(description = "ID do produto associado ao lote", example = "1")
    private long product_id;
}
