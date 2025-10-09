package com.api.dto.employee;

import com.api.Model.Employee;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Value;

@Value
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
}
