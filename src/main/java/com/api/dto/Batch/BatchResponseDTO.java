package com.api.dto.Batch;

import com.api.Model.Batch;
import com.api.dto.address.AddressResponseDTO;

import java.util.Date;

public class BatchResponseDTO {
    private long id;
    private String unit_measure;
    private Date entry_date;
    private String batch_code;
    private Date expiration_date;
    private int quantity;
    private long product_id;


    public BatchResponseDTO(long id, String unit_measure, Date entry_date, String batch_code, Date expiration_date, int quantity, long product_id) {
        this.id = id;
        this.unit_measure = unit_measure;
        this.entry_date = entry_date;
        this.batch_code = batch_code;
        this.expiration_date = expiration_date;
        this.quantity = quantity;
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

    @Override
    public String toString() {
        return "BatchResponseDTO{" +
                "id=" + id +
                ", unit_measure='" + unit_measure + '\'' +
                ", entry_date=" + entry_date +
                ", batch_code='" + batch_code + '\'' +
                ", expiration_date=" + expiration_date +
                ", quantity=" + quantity +
                ", product_id=" + product_id +
                '}';
    }
}
