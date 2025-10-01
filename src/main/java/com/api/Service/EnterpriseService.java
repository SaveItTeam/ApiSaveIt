package com.api.Service;

import com.api.Model.Enterprise;
import com.api.Repository.EnterpriseRepository;
import com.api.dto.enterprise.EnterpriseRequestDTO;
import com.api.dto.enterprise.EnterpriseResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class EnterpriseService {


    private final EnterpriseRepository enterpriseRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public EnterpriseService(EnterpriseRepository enterpriseRepository, ObjectMapper objectMapper, ObjectMapper objectMapper1) {
        this.enterpriseRepository = enterpriseRepository;
        this.objectMapper = objectMapper1;
    }

    public List<EnterpriseResponseDTO> listEnterprise(){
        List<Enterprise> enterprises = enterpriseRepository.findAll();
        List<EnterpriseResponseDTO> returnList = new ArrayList<>();
        for(Enterprise enterprise: enterprises){
            returnList.add(objectMapper.convertValue(enterprise, EnterpriseResponseDTO.class));
        }
        return returnList;
    }

    public EnterpriseResponseDTO findByEmail(String email) {
        return objectMapper.convertValue(enterpriseRepository.findByEmail(email), EnterpriseResponseDTO.class);
    }

    public EnterpriseResponseDTO insertEnterprise(EnterpriseRequestDTO enterprise) {
        Enterprise enterpriseRequest = objectMapper.convertValue(enterprise, Enterprise.class);
        Enterprise response = enterpriseRepository.save(enterpriseRequest);
        return objectMapper.convertValue(response, EnterpriseResponseDTO.class);
    }

    public void deleteEnterprise(Long id) {
        enterpriseRepository.deleteById(id);
    }


    public EnterpriseResponseDTO updateEnterprise(Long id, Enterprise enterpriseAtualizada) {
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

        enterpriseRepository.save(enterprise);
        EnterpriseResponseDTO enterpriseResponseDTO = objectMapper.convertValue(enterprise, EnterpriseResponseDTO.class);
        return enterpriseResponseDTO;
    }

    public EnterpriseResponseDTO updateEnterprisePartial(Long id, Map<String, Object> updates) {
        Enterprise enterprise = enterpriseRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Empresa com ID " + id + " não encontrado"));

        if (updates.containsKey("cnpj")) {
            enterprise.setCnpj((String) updates.get("cnpj"));
        }
        if (updates.containsKey("name")) {
            enterprise.setName((String) updates.get("name"));
        }
        if (updates.containsKey("plan_id")) {
            enterprise.setPlan_id((int) updates.get("plan_id"));
        }
        if (updates.containsKey("email")) {
            enterprise.setEmail((String) updates.get("email"));
        }
        if (updates.containsKey("phone_number")) {
            enterprise.setPhone_number((String) updates.get("phone_number"));
        }
        if (updates.containsKey("adress_id")) {
            enterprise.setAddress_id((long) updates.get("adress_id"));
        }
        if (updates.containsKey("password")) {
            enterprise.setPassword((String) updates.get("password"));
        }
        if (updates.containsKey("category")) {
            enterprise.setCategory((String) updates.get("category"));
        }

        enterpriseRepository.save(enterprise);
        EnterpriseResponseDTO enterpriseResponseDTO = objectMapper.convertValue(enterprise, EnterpriseResponseDTO.class);
        return enterpriseResponseDTO;
    }

}
