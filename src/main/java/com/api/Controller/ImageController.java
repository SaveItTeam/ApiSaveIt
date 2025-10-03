package com.api.Controller;

import com.api.Exception.GlobalException;
import com.api.Model.Image;
import com.api.Service.ImageService;

import com.api.dto.showcaseImage.ShowcaseImageResponseDTO;
import com.api.projection.ProductShowcaseProjection;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

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
public class ImageController {
    private final ImageService imageService;
    private GlobalException ge;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/selecionar")
    @Operation(summary = "Listar todas as imagens")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de imagens retornada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<List<ImageResponseDTO>> listImage() {
        List<ImageResponseDTO> images = imageService.listImage();
        return ResponseEntity.ok(images);
    }
    @GetMapping("/showcase-images/{showcaseId}")
    @Operation(summary = "Listar imagens para vitrine")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de imagens para vitrine retornada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<?> getShowcaseWithImages(@RequestParam Long showcaseId) {
        return ResponseEntity.ok(imageService.listShowcaseWithImages(showcaseId));
    }
    @PostMapping("/inserir")
    @Operation(summary = "Inserir uma nova imagem")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Imagem inserida com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<?> insertImage(@RequestBody ImageRequestDTO image) {
        imageService.insertImage(image);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Imagem inserido com sucesso!");
    }

    @GetMapping("/selecionar/{id}")
    @Operation(summary = "Obter uma imagem por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Imagem retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Imagem não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<ImageResponseDTO> getImageById(@PathVariable Long id) {
        ImageResponseDTO image = imageService.getImageById(id);
        return ResponseEntity.ok(image);
    }

    @GetMapping("/selecionarPorProduto/{productId}")
    @Operation(summary = "Obter uma imagem por ID do produto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Imagem retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Imagem não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<ImageResponseDTO> getImageByProductId(@PathVariable Long productId) {
        ImageResponseDTO image = imageService.getImageByProductId(productId);
        return ResponseEntity.ok(image);
    }

    @DeleteMapping("/excluir/{id}")
    @Operation(summary = "Excluir uma imagem")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Imagem excluída com sucesso"),
            @ApiResponse(responseCode = "404", description = "Imagem não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<?> deleteImage(@PathVariable Long id) {
        imageService.deleteImage(id);
        return ResponseEntity.ok("Imagem excluído com sucesso!");
    }
    @PutMapping("/atualizar/{id}")
    @Operation(summary = "Atualizar uma imagem")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Imagem atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "404", description = "Imagem não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<?> updateImage(@PathVariable Long id, @Valid @RequestBody ImageRequestDTO imageAtualizado) {
        imageService.updateImage(id, imageAtualizado);
        return ResponseEntity.ok("Imagem atualizada com sucesso!");
    }
    @PatchMapping("/atualizarParcial/{id}")
    @Operation(summary = "Atualizar parcialmente uma imagem")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Imagem atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "404", description = "Imagem não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<?> updateImagePartial(@PathVariable Long id, @Valid @RequestBody Map<String, Object> updates) {
        imageService.updateImagePartial(id, updates);
        return ResponseEntity.ok("Imagem atualizado parcialmente com sucesso!");
    }
}
