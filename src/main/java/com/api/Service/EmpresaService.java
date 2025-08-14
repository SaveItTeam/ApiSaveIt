package com.api.Service;

import com.api.Model.Empresa;
import com.api.Repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class EmpresaService {


    private final EmpresaRepository empresaRepository;

    @Autowired
    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }



    //    Métodos de busca
    public List<Empresa> listarEmpresa(){return empresaRepository.findAll();}

    // Inserção de enderecos
    public Empresa inserirEmpresa(Empresa empresa) {
        return empresaRepository.save(empresa);
    }

    // Deleção de endereços
    public void excluirEmpresa(Long id) {
        // Se o produto não for encontrado, pode ser lançado um erro posteriormente.
        // Dependendo da implementação do repository, pode ser necessário buscar primeiro o produto.
        //        Endereco existe = buscarProdutoPorId(id);
        empresaRepository.deleteById(id);
        //        return;
    }


    // Atualização de endereços
    public Empresa atualizarEmpresa(Long id, Empresa empresaAtualizada) {
        Empresa empresa = empresaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Endereco com ID " + id + " não encontrado"));

        empresa.setCnpj(empresaAtualizada.getCnpj());
        empresa.setNome(empresaAtualizada.getNome());
        empresa.setCodigo(empresaAtualizada.getCodigo());
        empresa.setEmail(empresaAtualizada.getEmail());
        empresa.setTipoUsuario(empresaAtualizada.getTipoUsuario());
        empresa.setTelefone(empresaAtualizada.getTelefone());
        empresa.setEndereco_id(empresaAtualizada.getEndereco_id());
        empresa.setSenha(empresaAtualizada.getSenha());
        empresa.setCategoria(empresaAtualizada.getCategoria());
        empresa.setIs_buyer(empresaAtualizada.isIs_buyer());

        return empresaRepository.save(empresa);
    }
    // Atualização de endereço parcial

    public Empresa atualizarEmpresaParcial(Long id, Map<String, Object> updates) {
        Empresa empresa = empresaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Empresa com ID " + id + " não encontrado"));

        if (updates.containsKey("cnpj")) {
            empresa.setCnpj((String) updates.get("cnpj"));
        }
        if (updates.containsKey("nome")) {
            empresa.setNome((String) updates.get("nome"));
        }
        if (updates.containsKey("codigo")) {
            empresa.setCodigo((String) updates.get("codigo"));
        }
        if (updates.containsKey("email")) {
            empresa.setEmail((String) updates.get("email"));
        }
        if (updates.containsKey("tipo_usuario")) {
            empresa.setTipoUsuario((String) updates.get("tipo_usuario"));
        }
        if (updates.containsKey("telefone")) {
            empresa.setTelefone((String) updates.get("telefone"));
        }
        if (updates.containsKey("endereco_id")) {
            empresa.setEndereco_id((long) updates.get("endereco_id"));
        }
        if (updates.containsKey("senha")) {
            empresa.setSenha((String) updates.get("senha"));
        }
        if (updates.containsKey("categoria")) {
            empresa.setCategoria((String) updates.get("categoria"));
        }
        if (updates.containsKey("is_buyer")) {
            empresa.setIs_buyer((boolean) updates.get("is_buyer"));
        }

        return empresaRepository.save(empresa);
    }

}
