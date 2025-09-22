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
    public ResponseEntity<List<BatchResponseDTO>> listBatch() {
        List<BatchResponseDTO> batches = batchService.listBatch();
        return ResponseEntity.ok(batches);
    }



    @PostMapping("/inserir")
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
    public ResponseEntity<List<BatchListDTO>> listProdutosLote(@PathVariable long enterpriseId) {
        return ResponseEntity.ok(batchService.listProductBatch(enterpriseId));
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
    public ResponseEntity<?> updateBatchPartial(@PathVariable Long id, @Valid @RequestBody Map<String, Object> updates) {
        BatchResponseDTO batchResponse = batchService.updateBatchPartial(id, updates);
        return ResponseEntity.ok("Lote atualizado parcialmente com sucesso!" + batchResponse);
    }


}
