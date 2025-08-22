package com.api.Controller;

import com.api.Exception.GlobalException;
import com.api.Model.Image;
import com.api.Service.ImageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/image")
public class ImageController {
    private final ImageService imageService;
    private GlobalException ge;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }


    @GetMapping("/selecionar")
    public ResponseEntity<List<Image>> listImage() {
        List<Image> images = imageService.listImage();
        return ResponseEntity.ok(images);
    }



    @PostMapping("/inserir")
    public ResponseEntity<?> insertImage(@RequestBody Image image) {
        Image imageSalvo = imageService.insertImage(image);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Imagem inserido com sucesso! ID: " + imageSalvo.getId());
    }



    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<?> deleteImage(@PathVariable Long id) {
        imageService.deleteImage(id);
        return ResponseEntity.ok("Imagem excluído com sucesso!");
    }



    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> updateImage(@PathVariable Long id, @Valid @RequestBody Image imageAtualizado) {
        imageService.updateImage(id, imageAtualizado);
        return ResponseEntity.ok("Imagem atualizada com sucesso!");
    }



    @PatchMapping("/atualizarParcial/{id}")
    public ResponseEntity<?> updateImagePartial(@PathVariable Long id, @Valid @RequestBody Map<String, Object> updates) {
        imageService.updateImagePartial(id, updates);
        return ResponseEntity.ok("Imagem atualizado parcialmente com sucesso!");
    }
}
