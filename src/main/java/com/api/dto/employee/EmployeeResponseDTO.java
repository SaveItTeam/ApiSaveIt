package com.api.dto.employee;

import com.api.Model.Employee;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;

public class EmployeeResponseDTO {
    private long id;
    private String name;
    private String email;
    private long enterprise_id;

    public EmployeeResponseDTO(long enterprise_id, String email, String name, long id) {
        this.enterprise_id = enterprise_id;
        this.email = email;
        this.name = name;
        this.id = id;
    }

    public static EmployeeResponseDTO toDTO(Employee employee) {
        return new EmployeeResponseDTO(employee.getEnterprise_id(), employee.getEmail(), employee.getName(), employee.getId());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getEnterprise_id() {
        return enterprise_id;
    }

    public void setEnterprise_id(long enterprise_id) {
        this.enterprise_id = enterprise_id;
    }

    @Override
    public String toString() {
        return "EmployeeResponseDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", enterprise_id=" + enterprise_id +
                '}';
    }
}
