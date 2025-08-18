package com.api.Service;

import com.api.Model.Address;
import com.api.Repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class EnderecoService {
    private final EnderecoRepository enderecoRepository;

    @Autowired
    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }


    //    Métodos de busca
    public List<Address> listarEnderecos(){return enderecoRepository.findAll();}

    // Inserção de enderecos
    public Address inserirEndereco(Address address) {
        return enderecoRepository.save(address);
    }

    // Deleção de endereços
    public void excluirEndereco(Long id) {
        // Se o produto não for encontrado, pode ser lançado um erro posteriormente.
        // Dependendo da implementação do repository, pode ser necessário buscar primeiro o produto.
        //        Endereco existe = buscarProdutoPorId(id);
        enderecoRepository.deleteById(id);
        //        return;
    }
    // Atualização de endereços
    public Address atualizarEndereco(Long id, Address addressAtualizado) {
        Address address = enderecoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Endereco com ID " + id + " não encontrado"));

        address.setNome(addressAtualizado.getNome());
        address.setState(addressAtualizado.getState());
        address.setPublic_place(addressAtualizado.getPublic_place());
        address.setCep(addressAtualizado.getCep());
        address.setNeighborhood(addressAtualizado.getNeighborhood());
        address.setComplement(addressAtualizado.getComplement());
        address.setLogradouro(addressAtualizado.getLogradouro());
        address.setNumber(addressAtualizado.getNumber());

        return enderecoRepository.save(address);
    }

    // Atualização de endereço parcial

    public Address atualizarEnderecoParcial(Long id, Map<String, Object> updates) {
        Address address = enderecoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Endereco com ID " + id + " não encontrado"));

        if (updates.containsKey("nome")) {
            address.setNome((String) updates.get("nome"));
        }
        if (updates.containsKey("cidade")) {
            address.setCity((String) updates.get("cidade"));
        }
        if (updates.containsKey("estado")) {
            address.setState((String) updates.get("estado"));
        }
        if (updates.containsKey("rua")) {
            address.setPublic_place((String) updates.get("rua"));
        }
        if (updates.containsKey("cep")) {
            address.setCep((String) updates.get("cep"));
        }
        if (updates.containsKey("bairro")) {
            address.setNeighborhood((String) updates.get("bairro"));
        }
        if (updates.containsKey("complemento")) {
            address.setComplement((String) updates.get("complemento"));
        }
        if (updates.containsKey("logradouro")) {
            address.setLogradouro((String) updates.get("logradouro"));
        }
        if (updates.containsKey("numero")) {
            address.setNumber((String) updates.get("numero"));
        }

        return enderecoRepository.save(address);
    }

}

