package com.api.dto.stock;

import lombok.Value;

@Value
public class StockSummary {
    int totalInput;
    int totalOutput;
    int totalDiscard;
    int monthOutput;
}
