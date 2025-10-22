package com.api.service;

import com.api.model.Enterprise;
import com.api.repository.EnterpriseRepository;
import com.api.dto.enterprise.EnterpriseRequestDTO;
import com.api.dto.enterprise.EnterpriseResponseDTO;
import com.api.validator.EnterpriseValidator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
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
        Enterprise enterprise = enterpriseRepository.findByEmail(email);
        if (enterprise == null) {
            throw new NoSuchElementException("Empresa com email " + email + " não encontrado");
        }
        return objectMapper.convertValue(objectMapper.convertValue(enterprise, EnterpriseResponseDTO.class), EnterpriseResponseDTO.class);
    }

    public EnterpriseResponseDTO findById(Long id) {
        Enterprise enterprise = enterpriseRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Empresa com ID " + id + " não encontrado"));
        return objectMapper.convertValue(enterprise, EnterpriseResponseDTO.class);
    }

    public EnterpriseResponseDTO insertEnterprise(EnterpriseRequestDTO enterprise) {
        Enterprise enterpriseRequest = objectMapper.convertValue(enterprise, Enterprise.class);
        try {
            Enterprise response = enterpriseRepository.save(enterpriseRequest);
            return objectMapper.convertValue(response, EnterpriseResponseDTO.class);
        }catch (DataIntegrityViolationException ex) {
            throw new DataIntegrityViolationException("Erro de integridade ao inserir a empresa: " + ex.getMessage());
        }catch (DataAccessException ex) {
            throw new DataAccessException("Erro de acesso a dados ao inserir a empresa: " + ex.getMessage()) {};
        }
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

        EnterpriseValidator validator = new EnterpriseValidator();
        validator.validate(updates);

        Enterprise enterprise = enterpriseRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Empresa com ID " + id + " não encontrado"));

        if (updates.containsKey("cnpj")) {
            enterprise.setCnpj((String) updates.get("cnpj"));
        }
        if (updates.containsKey("name")) {
            enterprise.setName((String) updates.get("name"));
        }
        if (updates.containsKey("planId")) {
            enterprise.setPlanId((int) updates.get("planId"));
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
