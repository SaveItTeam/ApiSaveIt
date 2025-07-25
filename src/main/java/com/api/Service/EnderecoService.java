package com.api.Service;

import com.api.Model.Endereco;
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
    public List<Endereco> listarEnderecos(){return enderecoRepository.findAll();}

    // Inserção de enderecos
    public Endereco inserirEndereco(Endereco endereco) {
        return enderecoRepository.save(endereco);
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
    public Endereco atualizarEndereco(Long id, Endereco enderecoAtualizado) {
        Endereco endereco = enderecoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Endereco com ID " + id + " não encontrado"));

        endereco.setNome(enderecoAtualizado.getNome());
        endereco.setEstado(enderecoAtualizado.getEstado());
        endereco.setRua(enderecoAtualizado.getRua());
        endereco.setCep(enderecoAtualizado.getCep());
        endereco.setBairro(enderecoAtualizado.getBairro());
        endereco.setComplemento(enderecoAtualizado.getComplemento());

        return enderecoRepository.save(endereco);
    }

    // Atualização de endereço parcial

    public Endereco atualizarEnderecoParcial(Long id, Map<String, Object> updates) {
        Endereco endereco = enderecoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Endereco com ID " + id + " não encontrado"));

        if (updates.containsKey("nome")) {
            endereco.setNome((String) updates.get("nome"));
        }
        if (updates.containsKey("cidade")) {
            endereco.setCidade((String) updates.get("cidade"));
        }
        if (updates.containsKey("estado")) {
            endereco.setEstado((String) updates.get("estado"));
        }
        if (updates.containsKey("rua")) {
            endereco.setRua((String) updates.get("rua"));
        }
        if (updates.containsKey("cep")) {
            endereco.setCep((String) updates.get("cep"));
        }
        if (updates.containsKey("bairro")) {
            endereco.setBairro((String) updates.get("bairro"));
        }
        if (updates.containsKey("complemento")) {
            endereco.setComplemento((String) updates.get("complemento"));
        }

        return enderecoRepository.save(endereco);
    }

}

