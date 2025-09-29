package com.api.dto.employee;

import com.api.Model.Employee;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;

public class EmployeeRequestDTO {
    @NotNull(message = "Nome vazio")
    @Column(length = 255)
    @Schema(description = "Nome do funcionário", example = "João Silva")
    private String name;
    @Column(length = 255)
    @NotNull(message = "Email vazio")
    @Schema(description = "email do funcionario", example = "exemplo@gmail.com")
    private String email;
    @NotNull(message = "Senha vazia")
    @Schema(description = "Senha do funcionário", example = "senha123")
    @Column(length = 255)
    private String password;
    @NotNull(message = "empresa_id vazio")
    @Schema(description = "ID da empresa do funcionário", example = "1")
    private long enterprise_id;
    @NotNull(message = "Is buyer vazio")
    private boolean is_buyer = false;
    public EmployeeRequestDTO() {
    }

    public static Employee toModel(EmployeeRequestDTO employee) {
        Employee employeeModel = new Employee();
        employeeModel.setName(employee.getName());
        employeeModel.setEmail(employee.getEmail());
        employeeModel.setPassword(employee.getPassword());
        employeeModel.setEnterprise_id(employee.getEnterprise_id());
        employeeModel.setIs_buyer(employee.getIs_buyer());
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
    public boolean getIs_buyer() {
        return is_buyer;
    }
    public void setIs_buyer(boolean is_buyer) {
        this.is_buyer = is_buyer;
    }
}
