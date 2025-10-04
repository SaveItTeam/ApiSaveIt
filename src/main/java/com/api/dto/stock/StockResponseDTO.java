package com.api.dto.stock;

import lombok.Data;
import lombok.Value;
import org.springframework.boot.convert.DataSizeUnit;

@Value
public class StockResponseDTO {
    private long id;
    private int quantity_input;
    private int quantity_output;
    private int batch_id;
}
