package com.api.Service;

import com.api.Model.Product;
import com.api.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public List<Product> listProduct(){return productRepository.findAll();}

    public Product insertProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
    public Product updateProduct(Long id, Product productAtualizado) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Produto com ID " + id + " não encontrado"));

        product.setSku(productAtualizado.getSku());
        product.setName(productAtualizado.getName());
        product.setBrand(productAtualizado.getBrand());
        product.setDescricao(productAtualizado.getDescricao());
        product.setEnterprise_id(productAtualizado.getEnterprise_id());

        return productRepository.save(product);
    }


    public Product updateProductPartial(Long id, Map<String, Object> updates) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Produto com ID " + id + " não encontrado"));

        if (updates.containsKey("sku")) {
            product.setSku((String) updates.get("sku"));
        }
        if (updates.containsKey("name")) {
            product.setName((String) updates.get("name"));
        }
        if (updates.containsKey("brand")) {
            product.setBrand((String) updates.get("brand"));
        }
        if (updates.containsKey("descricao")) {
            product.setDescricao((String) updates.get("descricao"));
        }
        if (updates.containsKey("enterprise_id")) {
            product.setEnterprise_id((long) updates.get("enterprise_id"));
        }

        return productRepository.save(product);
    }


}
