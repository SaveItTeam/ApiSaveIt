package com.api.Service;

import com.api.model.Address;
import com.api.Repository.AddressRepository;
import com.api.dto.address.AddressRequestDTO;
import com.api.dto.address.AddressResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class AddressService {
    private final AddressRepository addressRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public AddressService(AddressRepository addressRepository, ObjectMapper objectMapper, ObjectMapper objectMapper1) {
        this.addressRepository = addressRepository;
        this.objectMapper = objectMapper1;
    }


    public List<AddressResponseDTO> listAddress(){
        List<Address> addresses = addressRepository.findAll();
        List<AddressResponseDTO> addressResponseDTOS = new ArrayList<>();
        for (Address address : addresses) {
            addressResponseDTOS.add(objectMapper.convertValue(address, AddressResponseDTO.class));
        }
        return addressResponseDTOS;
    }

    public AddressResponseDTO insertAddress(AddressRequestDTO address) {
        Address addressResponse = objectMapper.convertValue(address, Address.class);
        Address address1 = addressRepository.save(addressResponse);
        return objectMapper.convertValue(address1, AddressResponseDTO.class);
    }

    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }

    public AddressResponseDTO updateAddress(Long id, AddressRequestDTO addressAtualizado) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Endereco com ID " + id + " não encontrado"));

        address.setState(addressAtualizado.getState());
        address.setPublicPlace(addressAtualizado.getPublicPlace());
        address.setCep(addressAtualizado.getCep());
        address.setNeighbourhood(addressAtualizado.getNeighbourhood());
        address.setCity(addressAtualizado.getCity());
        address.setComplement(addressAtualizado.getComplement());

        addressRepository.save(address);
        AddressResponseDTO addressResponseDTO = objectMapper.convertValue(address, AddressResponseDTO.class);
        return addressResponseDTO;
    }


    public AddressResponseDTO updateAddressPartial(Long id, Map<String, Object> updates) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Endereco com ID " + id + " não encontrado"));


        if (updates.containsKey("city")) {
            address.setCity((String) updates.get("city"));
        }
        if (updates.containsKey("state")) {
            address.setState((String) updates.get("state"));
        }
        if (updates.containsKey("publicPlace")) {
            address.setPublicPlace((String) updates.get("publicPlace"));
        }
        if (updates.containsKey("cep")) {
            address.setCep((String) updates.get("cep"));
        }
        if (updates.containsKey("neighbourhood")) {
            address.setNeighbourhood((String) updates.get("neighbourhood"));
        }
        if (updates.containsKey("complement")) {
            address.setComplement((String) updates.get("complement"));
        }
        if (updates.containsKey("houseNumber")) {
            address.setHouseNumber((Integer) updates.get("houseNumber"));
        }

        addressRepository.save(address);

        AddressResponseDTO addressResponseDTO = objectMapper.convertValue(address, AddressResponseDTO.class);
        return addressResponseDTO;
    }

}

