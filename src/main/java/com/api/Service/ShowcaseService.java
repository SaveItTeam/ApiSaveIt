package com.api.Service;

import com.api.Model.Showcase;
import com.api.Repository.ShowcaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
@Service
public class ShowcaseService {
    private final ShowcaseRepository showcaseRepository;

    @Autowired
    public ShowcaseService(ShowcaseRepository showcaseRepository) {
        this.showcaseRepository = showcaseRepository;
    }
    public List<Showcase> listarVitrine(){return showcaseRepository.findAll();}

    public Showcase inserirVitrine(Showcase showCase) {
        return showcaseRepository.save(showCase);
    }

    public void excluirVitrine(Long id) {
        showcaseRepository.deleteById(id);
    }
    public Showcase atualizarVitrine(Long id, Showcase showcaseAtualizado) {
        Showcase showCase = showcaseRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Vitrine com ID " + id + " não encontrado"));

        showCase.setDescription(showcaseAtualizado.getDescription());
        showCase.setPrice(showcaseAtualizado.getPrice());
        showCase.setBatch_id(showcaseAtualizado.getBatch_id());

        return showcaseRepository.save(showCase);
    }

    public Showcase atualizarVitrineParcial(Long id, Map<String, Object> updates) {
        Showcase showCase = showcaseRepository.findById(id)
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

        return showcaseRepository.save(showCase);
    }
}
