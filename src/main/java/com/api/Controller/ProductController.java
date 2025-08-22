package com.api.Controller;

import com.api.Exception.GlobalException;
import com.api.Model.Product;
import com.api.Service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    public ResponseEntity<List<Product>> listProduct() {
        List<Product> products = productService.listProduct();
        return ResponseEntity.ok(products);
    }



    @PostMapping("/inserir")
    public ResponseEntity<?> insertProduct(@RequestBody Product product) {
        Product productSalvo = productService.insertProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Produto inserido com sucesso! ID: " + productSalvo.getId());
    }



    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Produto excluído com sucesso!");
    }



    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @Valid @RequestBody Product productAtualizado) {
        productService.updateProduct(id, productAtualizado);
        return ResponseEntity.ok("Produto atualizado com sucesso!");
    }



    @PatchMapping("/atualizarParcial/{id}")
    public ResponseEntity<?> updateProductPartial(@PathVariable Long id, @Valid @RequestBody Map<String, Object> updates) {
        productService.updateProductPartial(id, updates);
        return ResponseEntity.ok("Produto atualizado parcialmente com sucesso!");
    }


}
