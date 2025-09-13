package com.api.dto.Batch;

import com.api.dto.image.ImageRequestDTO;
import com.api.dto.product.ProductRequestDTO;

public class BatchInsertRequestDTO {
    private BatchRequestDTO batch;
    private ProductRequestDTO product;
    private ImageRequestDTO image;

    public BatchInsertRequestDTO() {
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
}
