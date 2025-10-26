package com.api.dto.employee;

import io.swagger.v3.oas.annotations.media.Schema;
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
    private long enterpriseId;
    @Schema(description = "É admin campo bool", example = "true")
    private Boolean isAdmin;
}
