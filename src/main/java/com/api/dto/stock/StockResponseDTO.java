package com.api.dto.stock;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class StockResponseDTO {
    long id;
    Integer quantityInput;
    Integer quantityOutput;
    long batchId;
    long productId;
    Integer discardQuantity;
    String discardReason;
    LocalDateTime createdAt;
}
