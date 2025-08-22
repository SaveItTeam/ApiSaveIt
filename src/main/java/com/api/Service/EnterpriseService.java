package com.api.Service;

import com.api.Model.Enterprise;
import com.api.Repository.EnterpriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class EnterpriseService {


    private final EnterpriseRepository enterpriseRepository;

    @Autowired
    public EnterpriseService(EnterpriseRepository enterpriseRepository) {
        this.enterpriseRepository = enterpriseRepository;
    }

    public List<Enterprise> listEnterprise(){return enterpriseRepository.findAll();}

    public Enterprise insertEnterprise(Enterprise enterprise) {
        return enterpriseRepository.save(enterprise);
    }

    public void deleteEnterprise(Long id) {
        enterpriseRepository.deleteById(id);
    }


    public Enterprise updateEnterprise(Long id, Enterprise enterpriseAtualizada) {
        Enterprise enterprise = enterpriseRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Endereco com ID " + id + " não encontrado"));

        enterprise.setCnpj(enterpriseAtualizada.getCnpj());
        enterprise.setName(enterpriseAtualizada.getName());
        enterprise.setPlan_id(enterpriseAtualizada.getPlan_id());
        enterprise.setEmail(enterpriseAtualizada.getEmail());
        enterprise.setPhone_number(enterpriseAtualizada.getPhone_number());
        enterprise.setAddress_id(enterpriseAtualizada.getAddress_id());
        enterprise.setPassword(enterpriseAtualizada.getPassword());
        enterprise.setCategory(enterpriseAtualizada.getCategory());
        enterprise.setIs_buyer(enterpriseAtualizada.isIs_buyer());

        return enterpriseRepository.save(enterprise);
    }

    public Enterprise updateEnterprisePartial(Long id, Map<String, Object> updates) {
        Enterprise enterprise = enterpriseRepository.findById(id)
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

        return enterpriseRepository.save(enterprise);
    }

}
