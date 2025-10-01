package com.api.dto.showcase;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;

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

    public ShowcaseResponseDTO(long id, String description, double price, long batch_id, Date entrance_date) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.batch_id = batch_id;
        this.entrance_date = entrance_date;
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
}
