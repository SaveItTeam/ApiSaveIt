package com.api.service;

import com.api.dto.product.ProductRequestDTO;
import com.api.dto.product.ProductResponseDTO;
import com.api.dto.product.ProductShowcaseStatusDTO;
import com.api.model.Product;
import com.api.repository.EnterpriseRepository;
import com.api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final EnterpriseRepository enterpriseRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, EnterpriseRepository enterpriseRepository) {
        this.productRepository = productRepository;
        this.enterpriseRepository = enterpriseRepository;
    }

    private ProductResponseDTO mapToDTO(Product product) {
        long enterpriseId = product.getEnterprise() != null ? product.getEnterprise().getId() : 0;
        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getBrand(),
                product.getCategory(),
                product.getDescription(),
                enterpriseId
        );
    }

    public List<ProductResponseDTO> listProduct() {
        List<Product> products = productRepository.findAll();
        List<ProductResponseDTO> productResponseDTOs = new ArrayList<>();
        for (Product product : products) {
            productResponseDTOs.add(mapToDTO(product));
        }
        return productResponseDTOs;
    }

    public ProductResponseDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Produto com ID " + id + " não encontrado"));
        return mapToDTO(product);
    }

    public ProductResponseDTO insertProduct(ProductRequestDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setBrand(productDTO.getBrand());
        product.setCategory(productDTO.getCategory());
        product.setDescription(productDTO.getDescription());

        if (productDTO.getEnterpriseId() != null) {
            var enterprise = enterpriseRepository.findById(productDTO.getEnterpriseId())
                    .orElseThrow(() -> new NoSuchElementException("Empresa com ID " + productDTO.getEnterpriseId() + " não encontrada"));
            product.setEnterprise(enterprise);
        }

        productRepository.save(product);
        return mapToDTO(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO productDTO) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Produto com ID " + id + " não encontrado"));

        product.setName(productDTO.getName());
        product.setBrand(productDTO.getBrand());
        product.setCategory(productDTO.getCategory());
        product.setDescription(productDTO.getDescription());

        if (productDTO.getEnterpriseId() != null) {
            var enterprise = enterpriseRepository.findById(productDTO.getEnterpriseId())
                    .orElseThrow(() -> new NoSuchElementException("Empresa com ID " + productDTO.getEnterpriseId() + " não encontrada"));
            product.setEnterprise(enterprise);
        }

        productRepository.save(product);
        return mapToDTO(product);
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
        if (updates.containsKey("description")) {
            product.setDescription((String) updates.get("description"));
        }
        if (updates.containsKey("category")) {
            product.setCategory((String) updates.get("category"));
        }
        if (updates.containsKey("enterpriseId")) {
            Long enterpriseId = ((Number) updates.get("enterpriseId")).longValue();
            var enterprise = enterpriseRepository.findById(enterpriseId)
                    .orElseThrow(() -> new NoSuchElementException("Empresa com ID " + enterpriseId + " não encontrada"));
            product.setEnterprise(enterprise);
        }

        productRepository.save(product);
        return mapToDTO(product);
    }

    public List<ProductShowcaseStatusDTO> listProductsWithShowcaseStatusByEnterprise(Long enterpriseId) {
        return productRepository.findProductsWithShowcaseStatusByEnterprise(enterpriseId);
    }
}
