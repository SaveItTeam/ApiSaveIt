package com.api.dto.Batch;

import com.api.dto.image.ImageRequestDTO;
import com.api.dto.product.ProductRequestDTO;
import lombok.Data;

@Data
public class BatchInsertRequestDTO {
    private BatchRequestDTO batch;
    private ProductRequestDTO product;
    private ImageRequestDTO image;

    public BatchInsertRequestDTO() {
    }

    public BatchInsertRequestDTO(ImageRequestDTO image, ProductRequestDTO product, BatchRequestDTO batch) {
        this.image = image;
        this.product = product;
        this.batch = batch;
    }


    public BatchRequestDTO getBatch() {
        return batch;
    }

    public void setBatch(BatchRequestDTO batch) {
        this.batch = batch;
    }

    public ProductRequestDTO getProduct() {
        return product;
    }

    public void setProduct(ProductRequestDTO product) {
        this.product = product;
    }

    public ImageRequestDTO getImage() {
        return image;
    }

    public void setImage(ImageRequestDTO image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "BatchInsertRequestDTO{" +
                "batch=" + batch +
                ", product=" + product +
                ", image=" + image +
                '}';
    }
}
