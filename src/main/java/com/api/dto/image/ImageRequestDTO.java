package com.api.dto.image;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.boot.convert.DataSizeUnit;

@Data
public class ImageRequestDTO {
    @Column(unique = true)
    @Schema(description = "ID da imagem", example = "1")
    private long id;
    @NotNull(message = "Codigo binario vazia")
    @Schema(description = "Codigo binario da imagem", example = "menor ideia")
    private String image;
    @NotNull(message = "id do produto vazio")
    @Schema(description = "ID do produto associado à imagem", example = "1")
    private long productId;
    
}
