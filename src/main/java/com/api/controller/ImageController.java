package com.api.controller;

import com.api.exception.GlobalException;
import com.api.OpenAPI.ImageOpenApi;
import com.api.Service.ImageService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import com.api.dto.image.ImageRequestDTO;
import com.api.dto.image.ImageResponseDTO;

@RestController
@RequestMapping("/api/image")
public class ImageController implements ImageOpenApi {
    private final ImageService imageService;
    private GlobalException ge;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/selecionar")
    public ResponseEntity<List<ImageResponseDTO>> listImage() {
        List<ImageResponseDTO> images = imageService.listImage();
        return ResponseEntity.ok(images);
    }
    @GetMapping("/showcase-images/{showcaseId}")
    public ResponseEntity<?> getShowcaseWithImages(@PathVariable Long showcaseId) {
        return ResponseEntity.ok(imageService.listShowcaseWithImages(showcaseId));
    }
    @PostMapping("/inserir")
    public ResponseEntity<?> insertImage(@RequestBody ImageRequestDTO image) {
        imageService.insertImage(image);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Imagem inserido com sucesso!");
    }

    @GetMapping("/selecionar/{id}")
    public ResponseEntity<ImageResponseDTO> getImageById(@PathVariable Long id) {
        ImageResponseDTO image = imageService.getImageById(id);
        return ResponseEntity.ok(image);
    }

    @GetMapping("/selecionarPorProduto/{productId}")
    public ResponseEntity<ImageResponseDTO> getImageByProductId(@PathVariable Long productId) {
        ImageResponseDTO image = imageService.getImageByProductId(productId);
        return ResponseEntity.ok(image);
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<?> deleteImage(@PathVariable Long id) {
        imageService.deleteImage(id);
        return ResponseEntity.ok("Imagem excluído com sucesso!");
    }
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> updateImage(@PathVariable Long id, @Valid @RequestBody ImageRequestDTO imageAtualizado) {
        imageService.updateImage(id, imageAtualizado);
        return ResponseEntity.ok("Imagem atualizada com sucesso!");
    }
    @PatchMapping("/atualizarParcial/{id}")
    public ResponseEntity<?> updateImagePartial(@PathVariable Long id, @Valid @RequestBody Map<String, Object> updates) {
        imageService.updateImagePartial(id, updates);
        return ResponseEntity.ok("Imagem atualizado parcialmente com sucesso!");
    }
}
