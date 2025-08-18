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


    //    Métodos de busca
    public List<Address> listarEnderecos(){return addressRepository.findAll();}

    // Inserção de enderecos
    public Address inserirEndereco(Address address) {
        return addressRepository.save(address);
    }

    // Deleção de endereços
    public void excluirEndereco(Long id) {
        // Se o produto não for encontrado, pode ser lançado um erro posteriormente.
        // Dependendo da implementação do repository, pode ser necessário buscar primeiro o produto.
        //        Endereco existe = buscarProdutoPorId(id);
        addressRepository.deleteById(id);
        //        return;
    }
    // Atualização de endereços
    public Address atualizarEndereco(Long id, Address addressAtualizado) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Endereco com ID " + id + " não encontrado"));

        address.setNome(addressAtualizado.getNome());
        address.setState(addressAtualizado.getState());
        address.setPublic_place(addressAtualizado.getPublic_place());
        address.setCep(addressAtualizado.getCep());
        address.setNeighborhood(addressAtualizado.getNeighborhood());
        address.setComplement(addressAtualizado.getComplement());
        address.setLogradouro(addressAtualizado.getLogradouro());
        address.setNumber(addressAtualizado.getNumber());

        return addressRepository.save(address);
    }

    // Atualização de endereço parcial

    public Address atualizarEnderecoParcial(Long id, Map<String, Object> updates) {
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
        if (updates.containsKey("logradouro")) {
            address.setLogradouro((String) updates.get("logradouro"));
        }
        if (updates.containsKey("number")) {
            address.setNumber((String) updates.get("number"));
        }

        return addressRepository.save(address);
    }

}

