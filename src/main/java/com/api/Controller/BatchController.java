package com.api.Controller;

import com.api.Exception.GlobalException;
import com.api.Model.Batch;
import com.api.Service.AddressService;
import com.api.Service.BatchService;
import com.api.Service.ImageService;
import com.api.Service.ProductService;
import com.api.dto.Batch.BatchInsertRequestDTO;
import com.api.dto.Batch.BatchListDTO;
import com.api.dto.Batch.BatchRequestDTO;
import com.api.dto.Batch.BatchResponseDTO;
import com.api.dto.address.AddressResponseDTO;
import com.api.dto.image.ImageRequestDTO;
import com.api.dto.product.ProductRequestDTO;
import com.api.dto.product.ProductResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/lote")
public class BatchController {
    private final BatchService batchService;
    private final AddressService addressService;
    private final ProductService productService;
    private final ImageService imageService;
    private GlobalException ge;

    @Autowired
    public BatchController(BatchService batchService, AddressService addressService, ProductService productService, ImageService imageService) {
        this.batchService = batchService;
        this.addressService = addressService;
        this.productService = productService;
        this.imageService = imageService;
    }


    @GetMapping("/selecionar")
    @Operation(summary = "Listar todos os lotes")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Lista de lotes retornada com sucesso"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<List<BatchResponseDTO>> listBatch() {
        List<BatchResponseDTO> batches = batchService.listBatch();
        return ResponseEntity.ok(batches);
    }



    @PostMapping("/inserir")
    @Operation(summary = "Inserir um novo lote")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Lote inserido com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<?> insertBatch(@RequestBody BatchInsertRequestDTO batch) {
        ProductResponseDTO productResponse = productService.insertProduct(batch.getProduct());

        BatchRequestDTO batchRequestDTO = batch.getBatch();
        batchRequestDTO.setProduct_id(productResponse.getId());
        batchService.insertBatch(batchRequestDTO);

        ImageRequestDTO imageRequestDTO = batch.getImage();
        imageRequestDTO.setProduct_id(productResponse.getId());
        imageService.insertImage(imageRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Lote inserido com sucesso!");
    }

    @GetMapping("/listarProdutosLote/{enterpriseId}")
    @Operation(summary = "Listar produtos com seus lotes por ID da empresa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de produtos com lotes retornada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<List<BatchListDTO>> listProdutosLote(@PathVariable long enterpriseId) {
        return ResponseEntity.ok(batchService.listProductBatch(enterpriseId));
    }

    @DeleteMapping("/excluir/{id}")
    @Operation(summary = "Excluir um lote pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lote excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Lote não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<?> deleteBatch(@PathVariable Long id) {
        batchService.deleteBatch(id);
        return ResponseEntity.ok("Lote excluído com sucesso!");
    }



    @PutMapping("/atualizar/{id}")
    @Operation(summary = "Atualizar um lote pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lote atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "404", description = "Lote não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<?> updateBatch(@PathVariable Long id, @Valid @RequestBody BatchRequestDTO batchAtualizado) {
        BatchResponseDTO batchResponseDTO = batchService.updateBatch(id, batchAtualizado);
        return ResponseEntity.ok("Lote atualizado com sucesso!" + batchResponseDTO);
    }



    @PatchMapping("/atualizarParcial/{id}")
    @Operation(summary = "Atualizar parcialmente um lote pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lote atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "404", description = "Lote não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<?> updateBatchPartial(@PathVariable Long id, @Valid @RequestBody Map<String, Object> updates) {
        BatchResponseDTO batchResponse = batchService.updateBatchPartial(id, updates);
        return ResponseEntity.ok("Lote atualizado parcialmente com sucesso!" + batchResponse);
    }


}
