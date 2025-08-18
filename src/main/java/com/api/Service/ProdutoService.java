package com.api.Service;

import com.api.Model.Product;
import com.api.Repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }
    //    Métodos de busca
    public List<Product> listarProduto(){return produtoRepository.findAll();}

    // Inserção de enderecos
    public Product inserirProduto(Product product) {
        return produtoRepository.save(product);
    }

    // Deleção de endereços
    public void excluirProduto(Long id) {
        // Se o produto não for encontrado, pode ser lançado um erro posteriormente.
        // Dependendo da implementação do repository, pode ser necessário buscar primeiro o produto.
        //        Endereco existe = buscarProdutoPorId(id);
        produtoRepository.deleteById(id);
        //        return;
    }
    // Atualização de endereços
    public Product atualizarProduto(Long id, Product productAtualizado) {
        Product product = produtoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Produto com ID " + id + " não encontrado"));

        product.setSku(productAtualizado.getSku());
        product.setName(productAtualizado.getName());
        product.setBrand(productAtualizado.getBrand());
        product.setDescricao(productAtualizado.getDescricao());
        product.setEnterprise_id(productAtualizado.getEnterprise_id());

        return produtoRepository.save(product);
    }

    // Atualização de endereço parcial

    public Product atualizarProdutoParcial(Long id, Map<String, Object> updates) {
        Product product = produtoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Produto com ID " + id + " não encontrado"));

        if (updates.containsKey("sku")) {
            product.setSku((String) updates.get("sku"));
        }
        if (updates.containsKey("nome")) {
            product.setName((String) updates.get("nome"));
        }
        if (updates.containsKey("marca")) {
            product.setBrand((String) updates.get("marca"));
        }
        if (updates.containsKey("descricao")) {
            product.setDescricao((String) updates.get("descricao"));
        }
        if (updates.containsKey("empresa_id")) {
            product.setEnterprise_id((long) updates.get("empresa_id"));
        }

        return produtoRepository.save(product);
    }


}
