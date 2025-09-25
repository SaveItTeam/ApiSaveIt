package com.api.dto.image;

import io.swagger.v3.oas.annotations.media.Schema;

public class ImageResponseDTO {
    @Schema(description = "ID da imagem", example = "1")
    private long id;
    @Schema(description = "Codigo binario da imagem", example = "menor ideia")
    private String image;
    @Schema(description = "ID do produto associado à imagem", example = "1")
    private long product_id;

    public ImageResponseDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(long product_id) {
        this.product_id = product_id;
    }

    @Override
    public String toString() {
        return "ImageResponseDTO [id=" + id + ", image=" + image + ", product_id=" + product_id + "]";
    }

    
}
