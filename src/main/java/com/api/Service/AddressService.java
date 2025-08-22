package com.api.Service;

import com.api.Model.Address;
import com.api.Repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class AddressService {
    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }


    public List<Address> listAddress(){return addressRepository.findAll();}

    public Address insertAddress(Address address) {
        return addressRepository.save(address);
    }

    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }
    public Address updateAddress(Long id, Address addressAtualizado) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Endereco com ID " + id + " não encontrado"));

        address.setNome(addressAtualizado.getNome());
        address.setState(addressAtualizado.getState());
        address.setPublic_place(addressAtualizado.getPublic_place());
        address.setCep(addressAtualizado.getCep());
        address.setNeighborhood(addressAtualizado.getNeighborhood());
        address.setCity(addressAtualizado.getCity());
        address.setComplement(addressAtualizado.getComplement());
        address.setNumber(addressAtualizado.getNumber());

        return addressRepository.save(address);
    }


    public Address updateAddressPartial(Long id, Map<String, Object> updates) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Endereco com ID " + id + " não encontrado"));

        if (updates.containsKey("nome")) {
            address.setNome((String) updates.get("nome"));
        }
        if (updates.containsKey("city")) {
            address.setCity((String) updates.get("city"));
        }
        if (updates.containsKey("state")) {
            address.setState((String) updates.get("state"));
        }
        if (updates.containsKey("public_place")) {
            address.setPublic_place((String) updates.get("public_place"));
        }
        if (updates.containsKey("cep")) {
            address.setCep((String) updates.get("cep"));
        }
        if (updates.containsKey("neighborhood")) {
            address.setNeighborhood((String) updates.get("neighborhood"));
        }
        if (updates.containsKey("complement")) {
            address.setComplement((String) updates.get("complement"));
        }
        if (updates.containsKey("number")) {
            address.setNumber((String) updates.get("number"));
        }

        return addressRepository.save(address);
    }

}

