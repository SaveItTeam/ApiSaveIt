package com.api.Controller;

import com.api.Exception.GlobalException;
import com.api.Model.Endereco;
import com.api.Model.Produto;
import com.api.Service.EnderecoService;
import com.api.Service.ProdutoService;
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
    private final ProdutoService produtoService;
    private GlobalException ge;

    @Autowired
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }


    @GetMapping("/selecionar")
    public ResponseEntity<List<Produto>> listarProdutos() {
        // Se lançar uma RuntimeException aqui, o Spring vai chamar o método do GlobalException automaticamente
        List<Produto> produtos = produtoService.listarProduto();
        return ResponseEntity.ok(produtos);
    }



    @PostMapping("/inserir")
    public ResponseEntity<?> inserirProduto(@RequestBody Produto produto) {
        Produto produtoSalvo = produtoService.inserirProduto(produto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Produto inserido com sucesso! ID: " + produtoSalvo.getId());
    }



    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<?> excluirProduto(@PathVariable Long id) {
        produtoService.excluirProduto(id);
        return ResponseEntity.ok("Produto excluído com sucesso!");
    }



    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizarProduto(@PathVariable Long id, @Valid @RequestBody Produto produtoAtualizado) {
        produtoService.atualizarProduto(id, produtoAtualizado);
        return ResponseEntity.ok("Produto atualizado com sucesso!");
    }



    @PatchMapping("/atualizarParcial/{id}")
    public ResponseEntity<?> atualizarProdutoParcial(@PathVariable Long id, @Valid @RequestBody Map<String, Object> updates) {
        produtoService.atualizarProdutoParcial(id, updates);
        return ResponseEntity.ok("Produto atualizado parcialmente com sucesso!");
    }


}
