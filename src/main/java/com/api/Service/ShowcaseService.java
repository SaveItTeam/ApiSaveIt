package com.api.Service;

import com.api.Model.Showcase;
import com.api.Repository.ShowcaseRepository;
import com.api.dto.showcase.ShowcaseListDTO;
import com.api.dto.showcase.ShowcaseRequestDTO;
import com.api.dto.showcase.ShowcaseResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
@Service
public class ShowcaseService {
    private final ShowcaseRepository showcaseRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public ShowcaseService(ShowcaseRepository showcaseRepository, ObjectMapper objectMapper) {
        this.showcaseRepository = showcaseRepository;
        this.objectMapper = objectMapper;
    }
    public List<ShowcaseResponseDTO> listShowcase(){
        List<Showcase> showcases = showcaseRepository.findAll();
        List<ShowcaseResponseDTO> showcaseResponseDTOs = new ArrayList<>();
        for (Showcase showcase : showcases) {
            showcaseResponseDTOs.add(objectMapper.convertValue(showcase, ShowcaseResponseDTO.class));
        }
        return showcaseResponseDTOs;
    }

    public ShowcaseResponseDTO insertShowcase(ShowcaseRequestDTO showCase) {
        Showcase showcase = showcaseRepository.save(objectMapper.convertValue(showCase, Showcase.class));
        return objectMapper.convertValue(showcase, ShowcaseResponseDTO.class);
    }

    public List<ShowcaseListDTO> listShowcaseWithProduct(String category) {
        List<ShowcaseListDTO> showcaseListDTOS = new ArrayList<>();
        if (category.equals("Todos")) {
            showcaseListDTOS = showcaseRepository.findShowcaseWithProduct();
        }else {
            showcaseListDTOS = showcaseRepository.findShowcaseWithProductByCategory(category);
        }
        if (showcaseListDTOS.isEmpty()) {
            throw new NoSuchElementException("Nenhuma vitrine encontrada");
        }
        return showcaseListDTOS;
    }

    public void deleteShowcase(Long id) {
        showcaseRepository.deleteById(id);
    }
    public ShowcaseResponseDTO updateShowcase(Long id, ShowcaseRequestDTO showcaseAtualizado) {
        Showcase showCase = showcaseRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Vitrine com ID " + id + " não encontrado"));

        showCase.setDescription(showcaseAtualizado.getDescription());
        showCase.setPrice(showcaseAtualizado.getPrice());
        showCase.setBatchId(showcaseAtualizado.getBatch_id());
        showCase.setEntranceDate(showcaseAtualizado.getEntrance_date());

        showcaseRepository.save(showCase);
        ShowcaseResponseDTO showcaseResponseDTO = objectMapper.convertValue(showcaseAtualizado, ShowcaseResponseDTO.class);
        return showcaseResponseDTO;
    }

    public ShowcaseResponseDTO updateShowcasePartial(Long id, Map<String, Object> updates) {
        Showcase showCase = showcaseRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Vitrine com ID " + id + " não encontrado"));

        if (updates.containsKey("description")) {
            showCase.setDescription((String) updates.get("description"));
        }
        if (updates.containsKey("price")) {
            showCase.setPrice((double) updates.get("price"));
        }
        if (updates.containsKey("batch_id")) {
            showCase.setBatchId((long) updates.get("batch_id"));
        }
        if (updates.containsKey("entrance_date")) {
            showCase.setEntranceDate((java.util.Date) updates.get("entrance_date"));
        }

        showcaseRepository.save(showCase);
        return objectMapper.convertValue(showCase.getClass(), ShowcaseResponseDTO.class);
    }
}
