package com.api.Service;

import com.api.Model.Image;
import com.api.Repository.ImageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import com.api.dto.image.ImageResponseDTO;
import com.api.dto.image.ImageRequestDTO;

@Service
public class ImageService {
    private final ImageRepository imageRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public ImageService(ImageRepository imageRepository, ObjectMapper objectMapper) {
        this.imageRepository = imageRepository;
        this.objectMapper = objectMapper;
    }


    public List<ImageResponseDTO> listImage(){
        List<Image> images = imageRepository.findAll();
        List<ImageResponseDTO> imagesResponse = new ArrayList<>();
        for (Image image : images) {
            imagesResponse.add(objectMapper.convertValue(image, ImageResponseDTO.class));
        }
        return imagesResponse;
    }

    public void insertImage(ImageRequestDTO image) {
        Image imageResponse = objectMapper.convertValue(image, Image.class);
        imageRepository.save(imageResponse);
    }

    public void deleteImage(Long id) {
        imageRepository.deleteById(id);
    }
    public ImageResponseDTO updateImage(Long id, ImageRequestDTO imageAtualizado) {
        Image image = imageRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Imagem com ID " + id + " não encontrado"));

        image.setImage(imageAtualizado.getImage());
        image.setProduct_id(imageAtualizado.getProduct_id());

        
        imageRepository.save(image);
        return objectMapper.convertValue(image, ImageResponseDTO.class);
    }
    public ImageResponseDTO updateImagePartial(Long id, Map<String, Object> updates) {
        Image image = imageRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Image com ID " + id + " não encontrado"));

        if (updates.containsKey("image")) {
            image.setImage((String) updates.get("image"));
        }
        if (updates.containsKey("product_id")) {
            image.setProduct_id((long) updates.get("product_id"));
        }

        imageRepository.save(image);
        return objectMapper.convertValue(image, ImageResponseDTO.class);
    }
}
