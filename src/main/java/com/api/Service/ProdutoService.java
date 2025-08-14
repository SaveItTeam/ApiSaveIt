package com.api.Service;

import com.api.Model.Endereco;
import com.api.Model.Produto;
import com.api.Repository.EnderecoRepository;
import com.api.Repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    public List<Produto> listarProduto(){return produtoRepository.findAll();}

    // Inserção de enderecos
    public Produto inserirProduto(Produto produto) {
        return produtoRepository.save(produto);
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
    public Produto atualizarProduto(Long id, Produto produtoAtualizado) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Produto com ID " + id + " não encontrado"));

        produto.setSku(produtoAtualizado.getSku());
        produto.setNome(produtoAtualizado.getNome());
        produto.setMarca(produtoAtualizado.getMarca());
        produto.setDescricao(produtoAtualizado.getDescricao());
        produto.setEmpresa_id(produtoAtualizado.getEmpresa_id());

        return produtoRepository.save(produto);
    }

    // Atualização de endereço parcial

    public Produto atualizarProdutoParcial(Long id, Map<String, Object> updates) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Produto com ID " + id + " não encontrado"));

        if (updates.containsKey("sku")) {
            produto.setSku((String) updates.get("sku"));
        }
        if (updates.containsKey("nome")) {
            produto.setNome((String) updates.get("nome"));
        }
        if (updates.containsKey("marca")) {
            produto.setMarca((String) updates.get("marca"));
        }
        if (updates.containsKey("descricao")) {
            produto.setDescricao((String) updates.get("descricao"));
        }
        if (updates.containsKey("empresa_id")) {
            produto.setEmpresa_id((long) updates.get("empresa_id"));
        }

        return produtoRepository.save(produto);
    }


}
