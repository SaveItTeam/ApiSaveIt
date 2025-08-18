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
public class ProdutoController {
    private final ProductService productService;
    private GlobalException ge;

    @Autowired
    public ProdutoController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/selecionar")
    public ResponseEntity<List<Product>> listarProdutos() {
        // Se lançar uma RuntimeException aqui, o Spring vai chamar o método do GlobalException automaticamente
        List<Product> products = productService.listarProduto();
        return ResponseEntity.ok(products);
    }



    @PostMapping("/inserir")
    public ResponseEntity<?> inserirProduto(@RequestBody Product product) {
        Product productSalvo = productService.inserirProduto(product);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Produto inserido com sucesso! ID: " + productSalvo.getId());
    }



    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<?> excluirProduto(@PathVariable Long id) {
        productService.excluirProduto(id);
        return ResponseEntity.ok("Produto excluído com sucesso!");
    }



    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizarProduto(@PathVariable Long id, @Valid @RequestBody Product productAtualizado) {
        productService.atualizarProduto(id, productAtualizado);
        return ResponseEntity.ok("Produto atualizado com sucesso!");
    }



    @PatchMapping("/atualizarParcial/{id}")
    public ResponseEntity<?> atualizarProdutoParcial(@PathVariable Long id, @Valid @RequestBody Map<String, Object> updates) {
        productService.atualizarProdutoParcial(id, updates);
        return ResponseEntity.ok("Produto atualizado parcialmente com sucesso!");
    }


}
