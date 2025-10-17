package com.api.dto.employee;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
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
    private long enterpriseId;
    @NotNull(message = "Is buyer vazio")
    private boolean isAdmin = false;
}
