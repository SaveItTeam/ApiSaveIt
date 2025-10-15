package com.api.dto.product;

import com.api.dto.Batch.BatchResponseDTO;
import com.api.dto.image.ImageResponseDTO;
import lombok.Value;

@Value
public class ProductResponseInfoDTO {
    ProductResponseDTO productResponseDTO;
    BatchResponseDTO batchResponseDTO;
    ImageResponseDTO imageResponseDTO;
}
