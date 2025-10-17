package com.api.dto.showcase;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class ShowcaseRequestDTO {
    @Column(unique = true)
    @Schema(description = "ID da vitrine", example = "1")
    private long id;

    @Column(length = 500)
    @Schema(description = "Descrição do produto na vitrine", example = "Produto em promoção")
    private String description;

    @NotNull(message = "lote_id vazio")
    @Schema(description = "ID do lote associado à vitrine", example = "1")
    private long batchId;

    @NotNull(message = "Quantidade na vitrine vazia")
    @Schema(description = "Quantidade do produto na vitrine", example = "50")
    private Integer quantityShowcase;

    @NotNull(message = "entrance_showcase vazio")
    @Schema(description = "Data de entrada do produto na vitrine", example = "2023-10-01T10:00:00Z")
    private Date entranceShowcase;

    @NotNull(message = "Nome vazio")
    @Schema(description = "Nome do produto na vitrine", example = "Camiseta Promoção")
    private String name;

    @NotNull(message = "Data de entrada vazia")
    @Schema(description = "Data de entrada do produto na vitrine", example = "2023-10-01T10:00:00Z")
    private Date entranceDate;

    @NotNull(message = "Preço vazio")
    @Schema(description = "Preço do produto na vitrine", example = "99.99")
    private Double price;
}
