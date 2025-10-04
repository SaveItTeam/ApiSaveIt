package com.api.dto.product;

import lombok.Data;
import lombok.Value;

@Value
public class ProductResponseDTO {
    private long id;
    private String sku;
    private String name;
    private String brand;
    private String category;
    private String description;
    private long enterprise_id;
}
