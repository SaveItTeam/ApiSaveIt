package com.api.Controller;

import com.api.Exception.GlobalException;
import com.api.Model.Address;
import com.api.Service.AddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/endereco")
public class EnderecoController {

    private final AddressService addressService;
    private GlobalException ge;

    @Autowired
    public EnderecoController(AddressService addressService) {
            this.addressService = addressService;
    }


    @GetMapping("/selecionar")
    public ResponseEntity<List<Address>> listarEnderecos() {
        // Se lançar uma RuntimeException aqui, o Spring vai chamar o método do GlobalException automaticamente
        List<Address> addresses = addressService.listarEnderecos();
        return ResponseEntity.ok(addresses);
    }



    @PostMapping("/inserir")
    public ResponseEntity<?> inserirEndereco(@RequestBody Address address) {
        Address addressSalvo = addressService.inserirEndereco(address);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Endereco inserido com sucesso! ID: " + addressSalvo.getId());
    }



    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<?> excluirEndereco(@PathVariable Long id) {
        addressService.excluirEndereco(id);
        return ResponseEntity.ok("Endereco excluído com sucesso!");
    }



    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizarEndereco(@PathVariable Long id, @Valid @RequestBody Address addressAtualizado) {
        addressService.atualizarEndereco(id, addressAtualizado);
        return ResponseEntity.ok("Endereco atualizado com sucesso!");
    }



    @PatchMapping("/atualizarParcial/{id}")
    public ResponseEntity<?> atualizarEnderecoParcial(@PathVariable Long id, @Valid @RequestParam Map<String, Object> updates) {
        addressService.atualizarEnderecoParcial(id, updates);
        return ResponseEntity.ok("Endereco atualizado parcialmente com sucesso!");
    }



}