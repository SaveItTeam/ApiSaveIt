package com.api.dto.stock;

import lombok.Value;

@Value
public class StockQuantity {
    String productName;
    Integer quantity;
    Integer maxQuantity;
}
