package com.api.service;

import com.api.model.Employee;
import com.api.repository.EmployeeRepository;
import com.api.dto.employee.EmployeeRequestDTO;
import com.api.dto.employee.EmployeeResponseDTO;
import com.api.validator.EmployeeValidator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ObjectMapper objectMapper;
    private final FirebaseAuthService firebaseAuthService;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, ObjectMapper objectMapper, FirebaseAuthService firebaseAuthService) {
        this.employeeRepository = employeeRepository;
        this.objectMapper = objectMapper;
        this.firebaseAuthService = firebaseAuthService;
    }

    public List<EmployeeResponseDTO> listEmployee() {
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeResponseDTO> employeeResponseDTOs = new ArrayList<>();
        for (Employee employee : employees) {
            employeeResponseDTOs.add(objectMapper.convertValue(employee, EmployeeResponseDTO.class));
        }
        return employeeResponseDTOs;
    }

    public EmployeeResponseDTO findByEmail(String email) {
        Employee employee = employeeRepository.findByEmail(email);
        if (employee == null) {
            throw new NoSuchElementException("Funcionario com email " + email + " não encontrado");
        }
        return objectMapper.convertValue(employee, EmployeeResponseDTO.class);
    }
    public void insertEmployee(EmployeeRequestDTO employee) {
        Employee employeeRequest = objectMapper.convertValue(employee, Employee.class);
        try {
            employeeRepository.save(employeeRequest);
            firebaseAuthService.createUser(employee.getEmail(), employee.getPassword());
        }catch (DataIntegrityViolationException ex) {
            throw new DataIntegrityViolationException("Violação da integridade dos dados: " + ex.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    public EmployeeResponseDTO updateEmployee(Long id, EmployeeRequestDTO employeeDTO) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Funcionario com ID " + id + " não encontrado"));

        employee.setName(employeeDTO.getName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setPassword(employeeDTO.getPassword());
        employee.setEnterpriseId(employeeDTO.getEnterpriseId());
        employee.setIsAdmin(employeeDTO.getIsAdmin() != null ? employeeDTO.getIsAdmin() : employee.getIsAdmin());

        employeeRepository.save(employee);
        return objectMapper.convertValue(employee, EmployeeResponseDTO.class);
    }

    public EmployeeResponseDTO updateEmployeePartial(Long id, Map<String, Object> updates) {
        EmployeeValidator validator = new EmployeeValidator();
        validator.validate(updates);

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Funcionario com ID " + id + " não encontrado"));

        if (updates.containsKey("name")) {
            employee.setName((String) updates.get("name"));
        }
        if (updates.containsKey("email")) {
            employee.setEmail((String) updates.get("email"));
        }
        if (updates.containsKey("password")) {
            employee.setPassword((String) updates.get("password"));
        }
        if (updates.containsKey("enterpriseId")) {
            employee.setEnterpriseId(Long.parseLong(updates.get("enterpriseId").toString()));
        }
        if (updates.containsKey("isAdmin")) {
            employee.setIsAdmin(Boolean.parseBoolean(updates.get("isAdmin").toString()));
        }

        employeeRepository.save(employee);
        return objectMapper.convertValue(employee, EmployeeResponseDTO.class);
    }
}
