package com.api.dto.Batch;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

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

    public BatchRequestDTO() {
    }

    public BatchRequestDTO(String unit_measure, Date entry_date, String batch_code, Date expiration_date, int quantity_measure, long product_id) {
        this.unit_measure = unit_measure;
        this.entry_date = entry_date;
        this.batch_code = batch_code;
        this.expiration_date = expiration_date;
        this.quantity_measure = quantity_measure;
        this.product_id = product_id;
    }

    //    public static Batch toModel(BatchRequestDTO batchRequestDTO) {
//        Batch newBatch = new Batch();
//        newBatch.setUnite_measure(batchRequestDTO.unite_measure);
//        newBatch.setEntry_date(batchRequestDTO.entry_date);
//        newBatch.setBatch_code(batchRequestDTO.batch_code);
//        newBatch.setExpiriation_date(batchRequestDTO.expiriation_date);
//        newBatch.setQuantity(batchRequestDTO.quantity);
//        newBatch.setProduct_id(batchRequestDTO.product_id);
//        return newBatch;
//    }

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
        return "BatchRequestDTO{" +
                "unit_measure='" + unit_measure + '\'' +
                ", entry_date=" + entry_date +
                ", batch_code='" + batch_code + '\'' +
                ", expiration_date=" + expiration_date +
                ", quantity_measure=" + quantity_measure +
                ", product_id=" + product_id +
                '}';
    }
}
