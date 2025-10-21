package com.api.dto.batch;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.Date;

@Value
@AllArgsConstructor
public class BatchListDTO {
    private Long id;
    private Long batchId;
    private String name;
    private Date expirationDate;
    private String quantity;
    private String image;
}
