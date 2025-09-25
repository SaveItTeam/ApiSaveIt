package com.api.Controller;

import com.api.Exception.GlobalException;
import com.api.Model.Address;
import com.api.Service.AddressService;
import com.api.Service.BatchService;
import com.api.dto.address.AddressRequestDTO;
import com.api.dto.address.AddressResponseDTO;
import com.api.validator.OnCreate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/endereco")
public class AddressController {

    private final AddressService addressService;
    private final LocalValidatorFactoryBean defaultValidator;
    private final BatchService batchService;
    private GlobalException ge;

    @Autowired
    public AddressController(AddressService addressService, LocalValidatorFactoryBean defaultValidator, BatchService batchService) {
        this.addressService = addressService;
        this.defaultValidator = defaultValidator;
        this.batchService = batchService;
    }


    @GetMapping("/selecionar")
    @Operation(summary = "Listar todos os endereços")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de endereços retornada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<List<AddressResponseDTO>> listAddress() {
        List<AddressResponseDTO> addresses = addressService.listAddress();
        return ResponseEntity.ok(addresses);
    }


    @PostMapping("/inserir")
    @Operation(summary = "Inserir um novo endereço")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Endereço inserido com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<?> insertAddress(@Validated({OnCreate.class, Default.class}) @RequestBody AddressRequestDTO address) {
        addressService.insertAddress(address);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Endereco inserido com sucesso!");
    }


    @DeleteMapping("/excluir/{id}")
    @Operation(summary = "Excluir um endereço pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereço excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Endereço não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<?> deleteAddress(@PathVariable Long id) {
        addressService.deleteAddress(id);
        return ResponseEntity.ok("Endereco excluído com sucesso!");
    }


    @PutMapping("/atualizar/{id}")
    @Operation(summary = "Atualizar um endereço pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "404", description = "Endereço não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<?> updateAddress(@PathVariable Long id, @Valid @RequestBody AddressRequestDTO addressAtualizado) {
        AddressResponseDTO addressResponseDTO = addressService.updateAddress(id, addressAtualizado);
        return ResponseEntity.ok(addressResponseDTO);
    }


    @PatchMapping("/atualizarParcial/{id}")
    @Operation(summary = "Atualizar parcialmente um endereço pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "404", description = "Endereço não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<?> updateAddressPartial(@PathVariable Long id, @Valid @RequestParam Map<String, Object> updates) {
        AddressResponseDTO addressResponseDTO = addressService.updateAddressPartial(id, updates);
        return ResponseEntity.ok(addressResponseDTO);
    }


}