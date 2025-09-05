package com.api.dto.estock;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;

public class StockRequestDTO {
    @NotNull(message = "Campo vazio")
    @Column(unique = true)
    private long id;
    @NotNull(message = "Quantidade de estoque vazio")
    private int quantity;
    @NotNull(message = "Quantidade da saída esta vazia")
    private int quantity_output;
    @NotNull(message = "Codigo do LOTE esta vazia")
    private int batch_id;

    public StockRequestDTO() {
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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
        return "StockRequestDTO{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", quantity_output=" + quantity_output +
                ", batch_id=" + batch_id +
                '}';
    }
}
