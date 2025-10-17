package com.api.dto.stock;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class StockResponseDTO {
    private long id;
    private Integer quantityInput;
    private Integer quantityOutput;
    private long batchId;
    private long productId;
    private Integer discardQuantity;
    private String discardReason;
    private LocalDateTime createdAt;
}
