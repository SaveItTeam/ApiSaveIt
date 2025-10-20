package com.api.Service;

import com.api.model.Showcase;
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

    public List<ShowcaseListDTO> listShowcaseWithProductByEnterpriseId(long enterpriseId) {
        List<ShowcaseListDTO> showcaseListDTOS = showcaseRepository.findShowcaseWithProductByEnterpriseId(enterpriseId);
        if (showcaseListDTOS.isEmpty()) {
            throw new NoSuchElementException("Nenhuma vitrine encontrada para o ID da empresa: " + enterpriseId);
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
        showCase.setBatchId(showcaseAtualizado.getBatchId());
        showCase.setQuantityShowcase(showcaseAtualizado.getQuantityShowcase());
        showCase.setEntranceShowcase(showcaseAtualizado.getEntranceShowcase());
        showCase.setName(showcaseAtualizado.getName());
        showCase.setEntranceDate(showcaseAtualizado.getEntranceDate());
        showCase.setPrice(showcaseAtualizado.getPrice());

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
        if (updates.containsKey("batchId")) {
            showCase.setBatchId((long) updates.get("batch_id"));
        }
        if (updates.containsKey("quantity_showcase")) {
            showCase.setQuantityShowcase((Integer) updates.get("quantity_showcase"));
        }
        if (updates.containsKey("entranceShowcase")) {
            showCase.setEntranceShowcase((java.util.Date) updates.get("entranceShowcase"));
        }
        if (updates.containsKey("name")) {
            showCase.setName((String) updates.get("name"));
        }
        if (updates.containsKey("entranceDate")) {
            showCase.setEntranceDate((java.util.Date) updates.get("entranceDate"));
        }
        if (updates.containsKey("price")) {
            showCase.setPrice((double) updates.get("price"));
        }


        showcaseRepository.save(showCase);
        return objectMapper.convertValue(showCase.getClass(), ShowcaseResponseDTO.class);
    }
}
