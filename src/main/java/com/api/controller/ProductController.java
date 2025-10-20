package com.api.controller;

import com.api.exception.GlobalException;

import com.api.OpenAPI.ProdutoOpenAPI;
import com.api.Service.ProductService;
import com.api.validator.OnCreate;
import com.api.validator.OnPatch;

import jakarta.validation.groups.Default;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import com.api.dto.product.ProductRequestDTO;
import com.api.dto.product.ProductResponseDTO;

@RestController
@RequestMapping("/api/product")
public class ProductController implements ProdutoOpenAPI {
    private final ProductService productService;
    private GlobalException ge;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    public ResponseEntity<List<ProductResponseDTO>> listProduct() {
        List<ProductResponseDTO> products = productService.listProduct();
        return ResponseEntity.ok(products);
    }



    public ResponseEntity<?> insertProduct(@RequestBody @Validated({OnCreate.class, Default.class}) ProductRequestDTO product) {
        productService.insertProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Produto inserido com sucesso!");
    }




    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Produto excluído com sucesso!");
    }



    public ResponseEntity<?> updateProduct(@PathVariable Long id, @Validated({OnCreate.class, Default.class}) @RequestBody ProductRequestDTO productAtualizado) {
        productService.updateProduct(id, productAtualizado);
        return ResponseEntity.ok("Produto atualizado com sucesso!");
    }




    public ResponseEntity<?> updateProductPartial(@PathVariable Long id,  @Validated({OnPatch.class, Default.class})  @RequestBody Map<String, Object> updates) {
        ProductResponseDTO responseDTO = productService.updateProductPartial(id, updates);
        return ResponseEntity.ok("Produto atualizado parcialmente com sucesso! " + responseDTO);
    }


}
