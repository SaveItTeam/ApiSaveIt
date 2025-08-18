package com.api.Service;

import com.api.Model.Endereco;
import com.api.Model.Funcionario;
import com.api.Repository.EnderecoRepository;
import com.api.Repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class FuncionarioService {
    private final FuncionarioRepository funcionarioRepository;

    @Autowired
    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }


    //    Métodos de busca
    public List<Funcionario> listarFuncionario(){return funcionarioRepository.findAll();}

    public Funcionario inserirFuncionario(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    public void excluirFuncionario(Long id) {
        // Se o produto não for encontrado, pode ser lançado um erro posteriormente.
        // Dependendo da implementação do repository, pode ser necessário buscar primeiro o produto.
        //        Endereco existe = buscarProdutoPorId(id);
        funcionarioRepository.deleteById(id);
        //        return;
    }
    public Funcionario atualizarFuncionario(Long id, Funcionario funcionarioAtualizado) {
        Funcionario funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Funcionario com ID " + id + " não encontrado"));

        funcionario.setNome(funcionarioAtualizado.getNome());
        funcionario.setEmail(funcionarioAtualizado.getEmail());
        funcionario.setSenha(funcionarioAtualizado.getSenha());
        funcionario.setEmpresa_id(funcionarioAtualizado.getEmpresa_id());

        return funcionarioRepository.save(funcionario);
    }


    public Funcionario atualizarFuncionarioParcial(Long id, Map<String, Object> updates) {
        Funcionario funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Funcionario com ID " + id + " não encontrado"));

        if (updates.containsKey("nome")) {
            funcionario.setNome((String) updates.get("nome"));
        }
        if (updates.containsKey("email")) {
            funcionario.setEmail((String) updates.get("email"));
        }
        if (updates.containsKey("senha")) {
            funcionario.setSenha((String) updates.get("senha"));
        }
        if (updates.containsKey("empresa_id")) {
            funcionario.setEmpresa_id((long) updates.get("empresa_id"));
        }

        return funcionarioRepository.save(funcionario);
    }
}
