package com.api.dto.showcase;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

import java.util.Date;

@Value
public class ShowcaseResponseDTO {
    @Schema(description = "ID da vitrine", example = "1")
    private long id;

    @Schema(description = "Descrição do produto na vitrine", example = "Produto em promoção")
    private String description;

    @Schema(description = "ID do lote associado à vitrine", example = "1")
    private long batchId;

    @Schema(description = "Quantidade do produto na vitrine", example = "50")
    private Integer quantityShowcase;

    @Schema(description = "Data de entrada", example = "50")
    private Date entranceDate;
}
