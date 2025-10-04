package com.api.dto.Batch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

import java.util.Date;

@Value
@AllArgsConstructor
public class BatchListDTO {
    private Long id;
    private String name;
    private Date expiration_date;
    private int quantity;
    private String image;
}
