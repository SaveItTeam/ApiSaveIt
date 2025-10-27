package com.api.dto.showcaseImage;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ShowcaseImageResponseDTO {
    @Schema(description = "ID do produto", example = "1")
    private Long productId;
    @Schema(description = "Descrição do produto", example = "Descrição detalhada do produto")
    private String description;
    @Schema(description = "ID do lote associado ao produto", example = "1")
    private Long loteId;
    @Schema(description = "Nome do produto", example = "Nome do Produto")
    private String name;
    @Schema(description = "URL da imagem do produto", example = "http://example.com/imagem.jpg")
    private String image;
}
