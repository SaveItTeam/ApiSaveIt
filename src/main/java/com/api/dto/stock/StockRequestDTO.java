package com.api.dto.stock;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;

public class StockRequestDTO {
    @NotNull(message = "Campo vazio")
    @Column(unique = true)
    private long id;
    @NotNull(message = "Quantidade de entrada estoque vazio")
    private int quantity_input;
    @NotNull(message = "Quantidade da saída esta vazia")
    private int quantity_output;
    @NotNull(message = "Codigo do LOTE esta vazia")
    private int batch_id;

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
        return "StockRequestDTO{" +
                "id=" + id +
                ", quantity=" + quantity_input +
                ", quantity_output=" + quantity_output +
                ", batch_id=" + batch_id +
                '}';
    }
}
