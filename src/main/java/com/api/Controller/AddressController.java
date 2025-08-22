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
public class AddressController {

    private final AddressService addressService;
    private GlobalException ge;

    @Autowired
    public AddressController(AddressService addressService) {
            this.addressService = addressService;
    }


    @GetMapping("/selecionar")
    public ResponseEntity<List<Address>> listAddress() {
        List<Address> addresses = addressService.listAddress();
        return ResponseEntity.ok(addresses);
    }



    @PostMapping("/inserir")
    public ResponseEntity<?> insertAddress(@RequestBody Address address) {
        Address addressSalvo = addressService.insertAddress(address);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Endereco inserido com sucesso! ID: " + addressSalvo.getId());
    }



    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable Long id) {
        addressService.deleteAddress(id);
        return ResponseEntity.ok("Endereco excluído com sucesso!");
    }



    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> updateAddress(@PathVariable Long id, @Valid @RequestBody Address addressAtualizado) {
        addressService.updateAddress(id, addressAtualizado);
        return ResponseEntity.ok("Endereco atualizado com sucesso!");
    }



    @PatchMapping("/atualizarParcial/{id}")
    public ResponseEntity<?> updateAddressPartial(@PathVariable Long id, @Valid @RequestParam Map<String, Object> updates) {
        addressService.updateAddressPartial(id, updates);
        return ResponseEntity.ok("Endereco atualizado parcialmente com sucesso!");
    }



}