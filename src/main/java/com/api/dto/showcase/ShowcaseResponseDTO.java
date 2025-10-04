package com.api.dto.showcase;

import io.swagger.v3.oas.annotations.media.Schema;
import jdk.jfr.DataAmount;
import lombok.Data;
import lombok.Value;

import java.util.Date;

@Value
public class ShowcaseResponseDTO {
    @Schema(description = "ID da vitrine", example = "1")
    private long id;
    @Schema(description = "Descrição do produto na vitrine", example = "Produto em promoção")
    private String description;
    @Schema(description = "Preço do produto na vitrine", example = "99.99")
    private double price;
    @Schema(description = "ID do lote associado à vitrine", example = "1")
    private long batch_id;
    private Date entrance_date;
}
