package com.api.dto.showcase;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class ShowcaseRequestDTO {
    @Column(unique = true)
    @Schema(description = "ID da vitrine", example = "1")
    private long id;

    @Column(length = 500)
    @Schema(description = "Descrição do produto na vitrine", example = "Produto em promoção")
    private String description;

    @NotNull(message = "Preço vazio")
    @Schema(description = "Preço do produto na vitrine", example = "99.99")
    private double price;

    @NotNull(message = "lote_id vazio")
    @Schema(description = "ID do lote associado à vitrine", example = "1")
    private long batch_id;

    @NotNull(message = "Data de entrada vazia")
    @Schema(description = "Data de entrada do produto na vitrine", example = "2023-10-01T10:00:00Z")
    private Date entrance_date;
    public ShowcaseRequestDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getBatch_id() {
        return batch_id;
    }

    public void setBatch_id(long batch_id) {
        this.batch_id = batch_id;
    }
    public Date getEntrance_date() {
        return entrance_date;
    }
    public void setEntrance_date(Date entrance_date) {
        this.entrance_date = entrance_date;
    }
}
