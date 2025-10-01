package com.api.dto.employee;

import com.api.Model.Employee;
import io.swagger.v3.oas.annotations.media.Schema;

public class EmployeeResponseDTO {
    @Schema(description = "ID do funcionário", example = "1")
    private long id;
    @Schema(description = "Nome do funcionário", example = "João Silva")
    private String name;
    @Schema(description = "email do funcionario", example = "exemplo@gmail.com")
    private String email;
    @Schema(description = "ID da empresa do funcionário", example = "1")
    private long enterprise_id;
    @Schema(description = "É admin campo bool", example = "true")
    private boolean is_admin;
    public EmployeeResponseDTO(long enterprise_id, String email, String name, long id, boolean is_admin) {
        this.enterprise_id = enterprise_id;
        this.email = email;
        this.name = name;
        this.id = id;
        this.is_admin = is_admin;
    }

    public static EmployeeResponseDTO toDTO(Employee employee) {
        return new EmployeeResponseDTO(employee.getEnterprise_id(), employee.getEmail(), employee.getName(), employee.getId(), employee.getIs_admin());
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

    public boolean getIs_admin() {
        return is_admin;
    }

    public void setIs_admin(boolean is_admin) {
        this.is_admin = is_admin;
    }

    @Override
    public String toString() {
        return "EmployeeResponseDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", enterprise_id=" + enterprise_id +
                ", is_admin=" + is_admin +
                '}';
    }
}
