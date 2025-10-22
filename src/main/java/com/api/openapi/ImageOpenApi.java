package com.api.openapi;

import com.api.dto.image.ImageRequestDTO;
import com.api.dto.image.ImageResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface ImageOpenApi {

    @Operation(summary = "Listar todas as imagens")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de imagens retornada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<List<ImageResponseDTO>> listImage();


    @Operation(summary = "Listar imagens para vitrine")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de imagens para vitrine retornada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<?> getShowcaseWithImages(Long showcaseId);


    @Operation(summary = "Inserir uma nova imagem")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Imagem inserida com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<?> insertImage(@Valid ImageRequestDTO image);


    @Operation(summary = "Obter uma imagem por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Imagem retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Imagem não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<ImageResponseDTO> getImageById(Long id);


    @Operation(summary = "Obter uma imagem por ID do produto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Imagem retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Imagem não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
     ResponseEntity<ImageResponseDTO> getImageByProductId(Long productId);


    @Operation(summary = "Excluir uma imagem")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Imagem excluída com sucesso"),
            @ApiResponse(responseCode = "404", description = "Imagem não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<?> deleteImage(Long id);


    @Operation(summary = "Atualizar uma imagem")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Imagem atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "404", description = "Imagem não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<?> updateImage(Long id,@Valid ImageRequestDTO imageAtualizado);


    @Operation(summary = "Atualizar parcialmente uma imagem")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Imagem atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "404", description = "Imagem não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<?> updateImagePartial(Long id,Map<String, Object> updates);
}


