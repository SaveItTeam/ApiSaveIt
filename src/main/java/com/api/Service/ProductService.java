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
    //    Métodos de busca
    public List<Product> listarProduto(){return productRepository.findAll();}

    // Inserção de enderecos
    public Product inserirProduto(Product product) {
        return productRepository.save(product);
    }

    // Deleção de endereços
    public void excluirProduto(Long id) {
        // Se o produto não for encontrado, pode ser lançado um erro posteriormente.
        // Dependendo da implementação do repository, pode ser necessário buscar primeiro o produto.
        //        Endereco existe = buscarProdutoPorId(id);
        productRepository.deleteById(id);
        //        return;
    }
    // Atualização de endereços
    public Product atualizarProduto(Long id, Product productAtualizado) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Produto com ID " + id + " não encontrado"));

        product.setSku(productAtualizado.getSku());
        product.setName(productAtualizado.getName());
        product.setBrand(productAtualizado.getBrand());
        product.setDescricao(productAtualizado.getDescricao());
        product.setEnterprise_id(productAtualizado.getEnterprise_id());

        return productRepository.save(product);
    }

    // Atualização de endereço parcial

    public Product atualizarProdutoParcial(Long id, Map<String, Object> updates) {
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
