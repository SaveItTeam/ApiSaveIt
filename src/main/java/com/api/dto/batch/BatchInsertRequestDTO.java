package com.api.dto.batch;

import com.api.dto.image.ImageRequestDTO;
import com.api.dto.batch.BatchRequestDTO;
import com.api.dto.product.ProductRequestDTO;
import jakarta.validation.Valid;
import lombok.Data;

@Data
public class BatchInsertRequestDTO {
    @Valid
    private BatchRequestDTO batch;

    @Valid
    private ProductRequestDTO product;

    @Valid
    private ImageRequestDTO image;
}
