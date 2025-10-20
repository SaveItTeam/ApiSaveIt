package com.api.Service;

import java.util.ArrayList;

import com.api.model.Product;
import com.api.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import com.api.dto.product.ProductRequestDTO;
import com.api.dto.product.ProductResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public ProductService(ProductRepository productRepository, ObjectMapper objectMapper) {
        this.productRepository = productRepository;
        this.objectMapper = objectMapper;
    }
    public List<ProductResponseDTO> listProduct(){
        List<Product> products = productRepository.findAll();
        List<ProductResponseDTO> productResponseDTO = new ArrayList<>();
        for (Product product : products) {
            productResponseDTO.add(objectMapper.convertValue(product, ProductResponseDTO.class));
        }
        return productResponseDTO;
    }

    public ProductResponseDTO getProductById(Long id){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Produto com ID " + id + " não encontrado"));
        return objectMapper.convertValue(product, ProductResponseDTO.class);
    }

    public ProductResponseDTO insertProduct(ProductRequestDTO product) {
        Product productResponse = objectMapper.convertValue(product, Product.class);
        productRepository.save(productResponse);
        return objectMapper.convertValue(productResponse, ProductResponseDTO.class);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO productAtualizado) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Produto com ID " + id + " não encontrado"));


        product.setName(productAtualizado.getName());
        product.setBrand(productAtualizado.getBrand());
        product.setEnterpriseId(productAtualizado.getEnterpriseId());
        product.setDescription(productAtualizado.getDescription());
        product.setCategory(productAtualizado.getCategory());

        productRepository.save(product);
        return objectMapper.convertValue(product, ProductResponseDTO.class);
    }


    public ProductResponseDTO updateProductPartial(Long id, Map<String, Object> updates) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Produto com ID " + id + " não encontrado"));

        if (updates.containsKey("name")) {
            product.setName((String) updates.get("name"));
        }
        if (updates.containsKey("brand")) {
            product.setBrand((String) updates.get("brand"));
        }
        if (updates.containsKey("enterpriseId")) {
            product.setEnterpriseId((long) updates.get("enterpriseId"));
        }
        if (updates.containsKey("description")) {
            product.setDescription((String) updates.get("description"));
        }
        if( updates.containsKey("category")) {
            product.setCategory((String) updates.get("category"));
        }

        productRepository.save(product);
        return objectMapper.convertValue(product, ProductResponseDTO.class);
    }


}
