package com.api.Service;

import com.api.Model.ShowCase;
import com.api.Repository.VitrineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
@Service
public class ShowCaseService {
    private final VitrineRepository vitrineRepository;

    @Autowired
    public ShowCaseService(VitrineRepository vitrineRepository) {
        this.vitrineRepository = vitrineRepository;
    }
    //    Métodos de busca
    public List<ShowCase> listarVitrine(){return vitrineRepository.findAll();}

    // Inserção de enderecos
    public ShowCase inserirVitrine(ShowCase showCase) {
        return vitrineRepository.save(showCase);
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
    public ShowCase atualizarVitrine(Long id, ShowCase showCaseAtualizado) {
        ShowCase showCase = vitrineRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Vitrine com ID " + id + " não encontrado"));

        showCase.setDescription(showCaseAtualizado.getDescription());
        showCase.setPrice(showCaseAtualizado.getPrice());
        showCase.setBatch_id(showCaseAtualizado.getBatch_id());

        return vitrineRepository.save(showCase);
    }

    // Atualização de endereço parcial

    public ShowCase atualizarVitrineParcial(Long id, Map<String, Object> updates) {
        ShowCase showCase = vitrineRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Vitrine com ID " + id + " não encontrado"));

        if (updates.containsKey("description")) {
            showCase.setDescription((String) updates.get("description"));
        }
        if (updates.containsKey("price")) {
            showCase.setPrice((double) updates.get("price"));
        }
        if (updates.containsKey("batch_id")) {
            showCase.setBatch_id((long) updates.get("batch_id"));
        }

        return vitrineRepository.save(showCase);
    }
}
