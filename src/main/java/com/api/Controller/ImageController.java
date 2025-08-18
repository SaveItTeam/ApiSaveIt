package com.api.Controller;

import com.api.Exception.GlobalException;
import com.api.Model.Funcionario;
import com.api.Model.Image;
import com.api.Service.FuncionarioService;
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
    public ResponseEntity<List<Image>> listarImage() {
        // Se lançar uma RuntimeException aqui, o Spring vai chamar o método do GlobalException automaticamente
        List<Image> images = imageService.listarImage();
        return ResponseEntity.ok(images);
    }



    @PostMapping("/inserir")
    public ResponseEntity<?> inserirImage(@RequestBody Image image) {
        Image imageSalvo = imageService.inserirImage(image);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Imagem inserido com sucesso! ID: " + imageSalvo.getId());
    }



    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<?> excluirImage(@PathVariable Long id) {
        imageService.excluirImage(id);
        return ResponseEntity.ok("Imagem excluído com sucesso!");
    }



    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizarImagem(@PathVariable Long id, @Valid @RequestBody Image imageAtualizado) {
        imageService.atualizarImage(id, imageAtualizado);
        return ResponseEntity.ok("Imagem atualizada com sucesso!");
    }



    @PatchMapping("/atualizarParcial/{id}")
    public ResponseEntity<?> atualizarImageParcial(@PathVariable Long id, @Valid @RequestBody Map<String, Object> updates) {
        imageService.atualizarImageParcial(id, updates);
        return ResponseEntity.ok("Imagem atualizado parcialmente com sucesso!");
    }
}
