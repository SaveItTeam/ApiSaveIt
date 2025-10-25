package com.api.dto.stock;

import lombok.Value;

@Value
public class StockSummary {
    Long totalInput;
    Long totalOutput;
    Long totalDiscard;
    Integer monthOutput;
}
