package com.api.dto.employee;

import com.api.Model.Employee;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;

public class EmployeeRequestDTO {
    @NotNull(message = "Nome vazio")
    @Column(length = 255)
    private String name;
    @Column(length = 255)
    @NotNull(message = "Email vazio")
    private String email;
    @NotNull(message = "Senha vazia")
    @Column(length = 255)
    private String password;
    @NotNull(message = "empresa_id vazio")
    private long enterprise_id;

    public EmployeeRequestDTO() {
    }

    public static Employee toModel(EmployeeRequestDTO employee) {
        Employee employeeModel = new Employee();
        employeeModel.setName(employee.getName());
        employeeModel.setEmail(employee.getEmail());
        employeeModel.setPassword(employee.getPassword());
        employeeModel.setEnterprise_id(employee.getEnterprise_id());
        return employeeModel;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getEnterprise_id() {
        return enterprise_id;
    }

    public void setEnterprise_id(long enterprise_id) {
        this.enterprise_id = enterprise_id;
    }
}
