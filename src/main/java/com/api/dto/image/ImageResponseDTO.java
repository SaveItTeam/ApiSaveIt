package com.api.dto.image;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

@Value
public class ImageResponseDTO {
    @Schema(description = "ID da imagem", example = "1")
    private long id;
    @Schema(description = "Codigo binario da imagem", example = "menor ideia")
    private String image;
    @Schema(description = "ID do produto associado à imagem", example = "1")
    private long product_id;


}
