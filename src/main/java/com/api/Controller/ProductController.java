package com.api.Controller;

import com.api.Exception.GlobalException;
import com.api.Model.Product;
import com.api.Service.ProductService;
import com.api.validator.OnCreate;
import com.api.validator.OnPatch;

import jakarta.validation.Valid;
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
@RequestMapping("/api/produto")
public class ProductController {
    private final ProductService productService;
    private GlobalException ge;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/selecionar")
    public ResponseEntity<List<ProductResponseDTO>> listProduct() {
        List<ProductResponseDTO> products = productService.listProduct();
        return ResponseEntity.ok(products);
    }



    @PostMapping("/inserir")
    public ResponseEntity<?> insertProduct(@RequestBody @Validated({OnCreate.class, Default.class}) ProductRequestDTO product) {
        productService.insertProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Produto inserido com sucesso!");
    }



    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Produto excluído com sucesso!");
    }



    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @Validated({OnCreate.class, Default.class}) @RequestBody ProductRequestDTO productAtualizado) {
        productService.updateProduct(id, productAtualizado);
        return ResponseEntity.ok("Produto atualizado com sucesso!");
    }



    @PatchMapping("/atualizarParcial/{id}")
    public ResponseEntity<?> updateProductPartial(@PathVariable Long id,  @Validated({OnPatch.class, Default.class})  @RequestBody Map<String, Object> updates) {
        ProductResponseDTO responseDTO = productService.updateProductPartial(id, updates);
        return ResponseEntity.ok("Produto atualizado parcialmente com sucesso! " + responseDTO);
    }


}
