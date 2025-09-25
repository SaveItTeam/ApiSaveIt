package com.api.dto.showcaseImage;

import io.swagger.v3.oas.annotations.media.Schema;

public class ShowcaseImageResponseDTO {
    @Schema(description = "ID do produto", example = "1")
    private Long productId;
    @Schema(description = "Descrição do produto", example = "Descrição detalhada do produto")
    private String description;
    @Schema(description = "Preço do produto", example = "199.99")
    private Double price;
    @Schema(description = "ID do lote associado ao produto", example = "1")
    private Long loteId;
    @Schema(description = "Nome do produto", example = "Nome do Produto")
    private String name;
    @Schema(description = "URL da imagem do produto", example = "http://example.com/imagem.jpg")
    private String image;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getLoteId() {
        return loteId;
    }

    public void setLoteId(Long loteId) {
        this.loteId = loteId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
