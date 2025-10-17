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

    public EnterpriseResponseDTO findById(Long id) {
        Enterprise enterprise = enterpriseRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Empresa com ID " + id + " não encontrado"));
        return objectMapper.convertValue(enterprise, EnterpriseResponseDTO.class);
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
        enterprise.setPlanId(enterpriseAtualizada.getPlanId());
        enterprise.setEmail(enterpriseAtualizada.getEmail());
        enterprise.setPhoneNumber(enterpriseAtualizada.getPhoneNumber());
        enterprise.setAddressId(enterpriseAtualizada.getAddressId());
        enterprise.setPassword(enterpriseAtualizada.getPassword());

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
            enterprise.setPlanId((int) updates.get("plan_id"));
        }
        if (updates.containsKey("email")) {
            enterprise.setEmail((String) updates.get("email"));
        }
        if(updates.containsKey("enterpriseImage")) {
            enterprise.setEnterpriseImage((String) updates.get("enterpriseImage"));
        }
        if (updates.containsKey("phoneNumber")) {
            enterprise.setPhoneNumber((String) updates.get("phoneNumber"));
        }
        if (updates.containsKey("adressId")) {
            enterprise.setAddressId((long) updates.get("adressId"));
        }
        if (updates.containsKey("password")) {
            enterprise.setPassword((String) updates.get("password"));
        }


        enterpriseRepository.save(enterprise);
        EnterpriseResponseDTO enterpriseResponseDTO = objectMapper.convertValue(enterprise, EnterpriseResponseDTO.class);
        return enterpriseResponseDTO;
    }

}
