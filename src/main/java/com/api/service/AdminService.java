package com.api.service;

import com.api.dto.admin.AdminRequestDTO;
import com.api.dto.admin.AdminResponseDTO;
import com.api.model.Admin;
import com.api.model.Employee;
import com.api.repository.AdminRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class AdminService {
    private final AdminRepository adminRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public AdminService(AdminRepository adminRepository, ObjectMapper objectMapper) {
        this.adminRepository = adminRepository;
        this.objectMapper = objectMapper;
    }


    public List<AdminResponseDTO> listAdmin(){
        List<Admin> admins = adminRepository.findAll();
        List<AdminResponseDTO> adminResponseDTOS = new ArrayList<>();
        for (Admin admin : admins) {
            adminResponseDTOS.add(objectMapper.convertValue(admin, AdminResponseDTO.class));
        }
        return adminResponseDTOS;
    }

    public void insertAdmin(AdminRequestDTO admin) {
        Admin adminRequest = objectMapper.convertValue(admin, Admin.class);
        adminRepository.save(adminRequest);
    }

    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }
    public AdminResponseDTO updateAdmin(Long id, AdminRequestDTO adminAtualizado) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Admin com ID " + id + " não encontrado"));

        admin.setName(adminAtualizado.getName());
        admin.setEmail(adminAtualizado.getEmail());
        admin.setPassword(adminAtualizado.getPassword());

        adminRepository.save(admin);
        return objectMapper.convertValue(admin, AdminResponseDTO.class);
    }


    public AdminResponseDTO updateAdminPartial(Long id, Map<String, Object> updates) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Admin com ID " + id + " não encontrado"));

        if (updates.containsKey("name")) {
            admin.setName((String) updates.get("name"));
        }
        if (updates.containsKey("email")) {
            admin.setEmail((String) updates.get("email"));
        }
        if (updates.containsKey("password")) {
            admin.setPassword((String) updates.get("password"));
        }

        adminRepository.save(admin);
        return objectMapper.convertValue(admin, AdminResponseDTO.class);
    }
}
