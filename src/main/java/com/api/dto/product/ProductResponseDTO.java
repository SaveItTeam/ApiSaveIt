package com.api.dto.product;

import lombok.Value;

@Value
public class ProductResponseDTO {
    private long id;
    private String name;
    private String brand;
    private String category;
    private String description;
    private long enterpriseId;
}
