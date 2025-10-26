package com.api.controller;

import com.api.exception.GlobalException;
import com.api.openapi.BatchOpenApi;
import com.api.service.AddressService;
import com.api.service.BatchService;
import com.api.service.ImageService;
import com.api.service.ProductService;
import com.api.dto.batch.BatchInsertRequestDTO;
import com.api.dto.batch.BatchListDTO;
import com.api.dto.batch.BatchRequestDTO;
import com.api.dto.batch.BatchResponseDTO;
import com.api.dto.image.ImageRequestDTO;
import com.api.dto.image.ImageResponseDTO;
import com.api.dto.product.ProductResponseDTO;
import com.api.dto.product.ProductResponseInfoDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/batch")
public class BatchController implements BatchOpenApi {
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
    public ResponseEntity<List<BatchResponseDTO>> listBatch() {
        List<BatchResponseDTO> batches = batchService.listBatch();
        return ResponseEntity.ok(batches);
    }

    @PostMapping("/inserir")
    public ResponseEntity<?> insertBatch(@Valid @RequestBody BatchInsertRequestDTO batch) {
        batchService.insertBatchEntity(batch);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Lote inserido com sucesso!");
    }

    @GetMapping("/listarProdutosLote/{enterpriseId}")
    public ResponseEntity<List<BatchListDTO>> listProdutosLote(@PathVariable long enterpriseId) {
        return ResponseEntity.ok(batchService.listProductBatch(enterpriseId));
    }

    @GetMapping("/informacoesProduto/{id}")
    public ResponseEntity<ProductResponseInfoDTO> getProductInfo(@PathVariable Long id) {
        BatchResponseDTO batch = batchService.getBatchById(id);

        if (batch.getProductId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(null);
        }

        ProductResponseDTO product = productService.getProductById(batch.getProductId());
        ImageResponseDTO image = imageService.getImageByProductId(product.getId());

        ProductResponseInfoDTO productInfo = new ProductResponseInfoDTO(product, batch, image);
        return ResponseEntity.ok(productInfo);
    }


    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<?> deleteBatch(@PathVariable Long id) {
        batchService.deleteBatch(id);
        return ResponseEntity.ok("Lote excluído com sucesso!");
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> updateBatch(@PathVariable Long id, @Valid @RequestBody BatchRequestDTO batchAtualizado) {
        BatchResponseDTO batchResponseDTO = batchService.updateBatch(id, batchAtualizado);
        return ResponseEntity.ok("Lote atualizado com sucesso!" + batchResponseDTO);
    }

    @PatchMapping("/atualizarParcial/{id}")
    public ResponseEntity<?> updateBatchPartial(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        BatchResponseDTO batchResponse = batchService.updateBatchPartial(id, updates);
        return ResponseEntity.ok("Lote atualizado parcialmente com sucesso!" + batchResponse);
    }
}
