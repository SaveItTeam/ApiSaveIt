package com.api.service;

import com.api.model.Showcase;
import com.api.repository.BatchRepository;
import com.api.repository.ShowcaseRepository;
import com.api.dto.showcase.ShowcaseListDTO;
import com.api.dto.showcase.ShowcaseRequestDTO;
import com.api.dto.showcase.ShowcaseResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class ShowcaseService {

    private final ShowcaseRepository showcaseRepository;
    private final ObjectMapper objectMapper;
    private BatchRepository batchRepository;

    @Autowired
    public ShowcaseService(ShowcaseRepository showcaseRepository, ObjectMapper objectMapper, BatchRepository batchRepository) {
        this.showcaseRepository = showcaseRepository;
        this.objectMapper = objectMapper;
        this.batchRepository = batchRepository;
    }

    private ShowcaseResponseDTO mapToDTO(Showcase showcase) {
        long batchId = showcase.getBatch() != null ? showcase.getBatch().getId() : 0;

        return new ShowcaseResponseDTO(
                showcase.getId(),
                showcase.getDescription(),
                batchId,
                showcase.getQuantityShowcase(),
                showcase.getEntranceDate()
        );
    }
    public List<ShowcaseResponseDTO> listShowcase() {
        List<Showcase> showcases = showcaseRepository.findAll();
        List<ShowcaseResponseDTO> dtoList = new ArrayList<>();
        for (Showcase s : showcases) {
            dtoList.add(mapToDTO(s));
        }
        return dtoList;
    }

    public ShowcaseResponseDTO insertShowcase(ShowcaseRequestDTO showcaseDTO) {
        Showcase showcase = new Showcase();
        showcase.setDescription(showcaseDTO.getDescription());
        showcase.setQuantityShowcase(showcaseDTO.getQuantityShowcase());
        showcase.setEntranceDate(showcaseDTO.getEntranceDate());

        if (showcaseDTO.getBatchId() != null) {
            var batch = batchRepository.findById(showcaseDTO.getBatchId())
                    .orElseThrow(() -> new NoSuchElementException("Lote com ID " + showcaseDTO.getBatchId() + " não encontrado"));
            showcase.setBatch(batch);
        }

        showcaseRepository.save(showcase);
        return mapToDTO(showcase);
    }

    public List<ShowcaseListDTO> listShowcaseWithProduct(String category) {
        List<ShowcaseListDTO> showcaseListDTOS;
        if (category.equals("Todos")) {
            showcaseListDTOS = showcaseRepository.findShowcaseWithProduct();
        } else {
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
        Showcase showcase = showcaseRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Vitrine com ID " + id + " não encontrada"));

        showcase.setDescription(showcaseAtualizado.getDescription());
        showcase.setQuantityShowcase(showcaseAtualizado.getQuantityShowcase());
        showcase.setEntranceDate(showcaseAtualizado.getEntranceDate());

        if (showcaseAtualizado.getBatchId() != null) {
            var batch = batchRepository.findById(showcaseAtualizado.getBatchId())
                    .orElseThrow(() -> new NoSuchElementException("Lote com ID " + showcaseAtualizado.getBatchId() + " não encontrado"));
            showcase.setBatch(batch);
        }

        showcaseRepository.save(showcase);
        return mapToDTO(showcase);
    }

    public ShowcaseResponseDTO updateShowcasePartial(Long id, Map<String, Object> updates) {
        Showcase showcase = showcaseRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Vitrine com ID " + id + " não encontrada"));

        if (updates.containsKey("description")) {
            showcase.setDescription((String) updates.get("description"));
        }
        if (updates.containsKey("batchId")) {
            Long batchId = ((Number) updates.get("batchId")).longValue();
            var batch = batchRepository.findById(batchId)
                    .orElseThrow(() -> new NoSuchElementException("Lote com ID " + batchId + " não encontrado"));
            showcase.setBatch(batch);
        }
        if (updates.containsKey("quantityShowcase")) {
            showcase.setQuantityShowcase((Integer) updates.get("quantityShowcase"));
        }
        if (updates.containsKey("entranceDate")) {
            showcase.setEntranceDate((java.util.Date) updates.get("entranceDate"));
        }

        showcaseRepository.save(showcase);
        return mapToDTO(showcase);
    }
    public List<ShowcaseResponseDTO> listNewShowcases(LocalDateTime lastCheck) {
        List<Showcase> novasVitrines = showcaseRepository.findAllByEntranceDateAfter(lastCheck);
        List<ShowcaseResponseDTO> resposta = new ArrayList<>();

        for (Showcase s : novasVitrines) {
            resposta.add(mapToDTO(s));
        }

        return resposta;
    }

}
