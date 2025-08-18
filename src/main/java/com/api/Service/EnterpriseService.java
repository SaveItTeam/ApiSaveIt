package com.api.Service;

import com.api.Model.Enterprise;
import com.api.Repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class EnterpriseService {


    private final EmpresaRepository empresaRepository;

    @Autowired
    public EnterpriseService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }



    //    Métodos de busca
    public List<Enterprise> listarEmpresa(){return empresaRepository.findAll();}

    // Inserção de enderecos
    public Enterprise inserirEmpresa(Enterprise enterprise) {
        return empresaRepository.save(enterprise);
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
    public Enterprise atualizarEmpresa(Long id, Enterprise enterpriseAtualizada) {
        Enterprise enterprise = empresaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Endereco com ID " + id + " não encontrado"));

        enterprise.setCnpj(enterpriseAtualizada.getCnpj());
        enterprise.setName(enterpriseAtualizada.getName());
        enterprise.setPlan_id(enterpriseAtualizada.getPlan_id());
        enterprise.setEmail(enterpriseAtualizada.getEmail());
        enterprise.setTipoUsuario(enterpriseAtualizada.getTipoUsuario());
        enterprise.setPhone_number(enterpriseAtualizada.getPhone_number());
        enterprise.setAddress_id(enterpriseAtualizada.getAddress_id());
        enterprise.setPassword(enterpriseAtualizada.getPassword());
        enterprise.setCategory(enterpriseAtualizada.getCategory());
        enterprise.setIs_buyer(enterpriseAtualizada.isIs_buyer());

        return empresaRepository.save(enterprise);
    }
    // Atualização de endereço parcial

    public Enterprise atualizarEmpresaParcial(Long id, Map<String, Object> updates) {
        Enterprise enterprise = empresaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Empresa com ID " + id + " não encontrado"));

        if (updates.containsKey("cnpj")) {
            enterprise.setCnpj((String) updates.get("cnpj"));
        }
        if (updates.containsKey("name")) {
            enterprise.setName((String) updates.get("name"));
        }
        if (updates.containsKey("plan_id")) {
            enterprise.setPlan_id((String) updates.get("plan_id"));
        }
        if (updates.containsKey("email")) {
            enterprise.setEmail((String) updates.get("email"));
        }
        if (updates.containsKey("tipo_usuario")) {
            enterprise.setTipoUsuario((String) updates.get("tipo_usuario"));
        }
        if (updates.containsKey("phone_number")) {
            enterprise.setPhone_number((String) updates.get("phone_number"));
        }
        if (updates.containsKey("endereco_id")) {
            enterprise.setAddress_id((long) updates.get("endereco_id"));
        }
        if (updates.containsKey("password")) {
            enterprise.setPassword((String) updates.get("password"));
        }
        if (updates.containsKey("category")) {
            enterprise.setCategory((String) updates.get("category"));
        }
        if (updates.containsKey("is_buyer")) {
            enterprise.setIs_buyer((boolean) updates.get("is_buyer"));
        }

        return empresaRepository.save(enterprise);
    }

}
