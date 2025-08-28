package com.api.dto.Batch;

import com.api.Model.Batch;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class BatchRequestDTO {
    @Column(length = 255)
    @NotNull(message = "Unidade de medida vazio")
    private String unite_measure;
    @NotNull(message = "Data de entrada vazia vazio")
    private Date entry_date;
    @Column(length = 255)
    @NotNull(message = "Batch_code vazio")
    private String batch_code;
    @Column(length = 100)
    @NotNull(message = "Data de validade vazio")
    private Date expiriation_date;
    @Column(length = 50)
    @NotNull(message = "Quantidade vazio")
    private int quantity;
    @Column(length = 20)
    @NotNull(message = "Id do produto vazio")
    private long product_id;

    public BatchRequestDTO() {
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

    public String getUnite_measure() {
        return unite_measure;
    }

    public void setUnite_measure(String unite_measure) {
        this.unite_measure = unite_measure;
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

    public Date getExpiriation_date() {
        return expiriation_date;
    }

    public void setExpiriation_date(Date expiriation_date) {
        this.expiriation_date = expiriation_date;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(long product_id) {
        this.product_id = product_id;
    }
}
