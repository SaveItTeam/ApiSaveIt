package com.api.dto.stock;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
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
}
