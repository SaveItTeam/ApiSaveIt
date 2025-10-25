package com.api.dto.batch;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.Date;

@Value
@AllArgsConstructor
public class BatchListDTO {
    Long id;
    Long batchId;
    String name;
    Date expirationDate;
    String quantity;
    String image;
}
