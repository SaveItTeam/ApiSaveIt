package com.api.dto.image;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;

public class ImageRequestDTO {
    @Column(unique = true)
    @Schema(description = "ID da imagem", example = "1")
    private long id;
    @NotNull(message = "Codigo binario vazia")
    @Schema(description = "Codigo binario da imagem", example = "menor ideia")
    private String image;
    @NotNull(message = "id do produto vazio")
    @Schema(description = "ID do produto associado à imagem", example = "1")
    private long product_id;

    public ImageRequestDTO(long id, String image, long product_id) {
        this.id = id;
        this.image = image;
        this.product_id = product_id;
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
        return "ImageRequestDTO [id=" + id + ", image=" + image + ", product_id=" + product_id + "]";
    }
    
    
}
