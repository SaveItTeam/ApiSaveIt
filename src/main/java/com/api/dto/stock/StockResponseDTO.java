package com.api.dto.stock;

public class StockResponseDTO {
    private long id;
    private int quantity_input;
    private int quantity_output;
    private int batch_id;

    public StockResponseDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getQuantity_input() {
        return quantity_input;
    }

    public void setQuantity_input(int quantity_input) {
        this.quantity_input = quantity_input;
    }

    public int getQuantity_output() {
        return quantity_output;
    }

    public void setQuantity_output(int quantity_output) {
        this.quantity_output = quantity_output;
    }

    public int getBatch_id() {
        return batch_id;
    }

    public void setBatch_id(int batch_id) {
        this.batch_id = batch_id;
    }

    @Override
    public String toString() {
        return "StockResponseDTO{" +
                "id=" + id +
                ", quantity=" + quantity_input +
                ", quantity_output=" + quantity_output +
                ", batch_id=" + batch_id +
                '}';
    }
}
