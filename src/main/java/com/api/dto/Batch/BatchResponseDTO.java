package com.api.dto.Batch;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

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


    public BatchResponseDTO(long id, String unit_measure, Date entry_date, String batch_code, Date expiration_date, int quantity_measure, long product_id) {
        this.id = id;
        this.unit_measure = unit_measure;
        this.entry_date = entry_date;
        this.batch_code = batch_code;
        this.expiration_date = expiration_date;
        this.quantity_measure = quantity_measure;
        this.product_id = product_id;
    }

//    public static BatchResponseDTO toDTO(Batch batch) {
//        return new BatchResponseDTO(batch.getId(),batch.getUnite_measure(), batch.getEntry_date(), batch.getBatch_code(), batch.getExpiriation_date(), batch.getQuantity(), batch.getProduct_id());
//    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUnit_measure() {
        return unit_measure;
    }

    public void setUnit_measure(String unit_measure) {
        this.unit_measure = unit_measure;
    }

    public Date getEntry_date() {
        return entry_date;
    }

    public void setEntry_date(Date entry_date) {
        this.entry_date = entry_date;
    }

    public String getBatch_code() {
        return batch_code;
    }

    public void setBatch_code(String batch_code) {
        this.batch_code = batch_code;
    }

    public Date getExpiration_date() {
        return expiration_date;
    }

    public void setExpiration_date(Date expiration_date) {
        this.expiration_date = expiration_date;
    }

    public int getQuantity_measure() {
        return quantity_measure;
    }

    public void setQuantity_measure(int quantity_measure) {
        this.quantity_measure = quantity_measure;
    }

    public long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(long product_id) {
        this.product_id = product_id;
    }

    @Override
    public String toString() {
        return "BatchResponseDTO{" +
                "id=" + id +
                ", unit_measure='" + unit_measure + '\'' +
                ", entry_date=" + entry_date +
                ", batch_code='" + batch_code + '\'' +
                ", expiration_date=" + expiration_date +
                ", quantity_measure=" + quantity_measure +
                ", product_id=" + product_id +
                '}';
    }
}
