package com.api.Service;

import com.api.model.Employee;
import com.api.Repository.EmployeeRepository;
import com.api.dto.employee.EmployeeRequestDTO;
import com.api.dto.employee.EmployeeResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, ObjectMapper objectMapper) {
        this.employeeRepository = employeeRepository;
        this.objectMapper = objectMapper;
    }


    public List<EmployeeResponseDTO> listEmployee(){
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeResponseDTO> employeeResponseDTOs = new ArrayList<>();
        for (Employee employee : employees) {
            employeeResponseDTOs.add(objectMapper.convertValue(employee, EmployeeResponseDTO.class));
        }
        return employeeResponseDTOs;
    }

    public void insertEmployee(EmployeeRequestDTO employee) {
        Employee employeeRequest = objectMapper.convertValue(employee, Employee.class);
        employeeRepository.save(employeeRequest);
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
    public EmployeeResponseDTO updateEmployee(Long id, EmployeeRequestDTO employeeAtualizado) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Funcionario com ID " + id + " não encontrado"));

        employee.setName(employeeAtualizado.getName());
        employee.setEmail(employeeAtualizado.getEmail());
        employee.setPassword(employeeAtualizado.getPassword());
        employee.setEnterpriseId(employeeAtualizado.getEnterpriseId());

        employeeRepository.save(employee);
        return objectMapper.convertValue(employee, EmployeeResponseDTO.class);
    }


    public EmployeeResponseDTO updateEmployeePartial(Long id, Map<String, Object> updates) {
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
            employee.setEnterpriseId((long) updates.get("enterpriseId"));
        }
        if (updates.containsKey("isAdmin")) {
            employee.setAdmin((boolean) updates.get("isAdmin"));
        }

        employeeRepository.save(employee);
        return objectMapper.convertValue(employee, EmployeeResponseDTO.class);
    }
}
