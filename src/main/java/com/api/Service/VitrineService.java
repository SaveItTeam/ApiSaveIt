package com.api.Service;

import com.api.Model.Produto;
import com.api.Model.Vitrine;
import com.api.Repository.ProdutoRepository;
import com.api.Repository.VitrineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
@Service
public class VitrineService {
    private final VitrineRepository vitrineRepository;

    @Autowired
    public VitrineService(VitrineRepository vitrineRepository) {
        this.vitrineRepository = vitrineRepository;
    }
    //    Métodos de busca
    public List<Vitrine> listarVitrine(){return vitrineRepository.findAll();}

    // Inserção de enderecos
    public Vitrine inserirVitrine(Vitrine vitrine) {
        return vitrineRepository.save(vitrine);
    }

    // Deleção de endereços
    public void excluirVitrine(Long id) {
        // Se o produto não for encontrado, pode ser lançado um erro posteriormente.
        // Dependendo da implementação do repository, pode ser necessário buscar primeiro o produto.
        //        Endereco existe = buscarProdutoPorId(id);
        vitrineRepository.deleteById(id);
        //        return;
    }
    // Atualização de endereços
    public Vitrine atualizarVitrine(Long id, Vitrine vitrineAtualizado) {
        Vitrine vitrine = vitrineRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Vitrine com ID " + id + " não encontrado"));

        vitrine.setDescricao(vitrineAtualizado.getDescricao());
        vitrine.setPreco(vitrineAtualizado.getPreco());
        vitrine.setLote_id(vitrineAtualizado.getLote_id());

        return vitrineRepository.save(vitrine);
    }

    // Atualização de endereço parcial

    public Vitrine atualizarVitrineParcial(Long id, Map<String, Object> updates) {
        Vitrine vitrine = vitrineRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Vitrine com ID " + id + " não encontrado"));

        if (updates.containsKey("descricao")) {
            vitrine.setDescricao((String) updates.get("descricao"));
        }
        if (updates.containsKey("preco")) {
            vitrine.setPreco((double) updates.get("preco"));
        }
        if (updates.containsKey("lote_id")) {
            vitrine.setLote_id((long) updates.get("lote_id"));
        }

        return vitrineRepository.save(vitrine);
    }
}
