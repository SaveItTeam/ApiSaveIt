package com.api.dto.admin;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

@Value
public class AdminResponseDTO {
    @Schema(description = "ID do administrador", example = "1")
    private Long id;
    @Schema(description = "Email de usuário do administrador", example = "adminuser@gmail.com")
    private String email;
    @Schema(description = "Nome completo do administrador", example = "Administrador Exemplo")
    private String name;
    @Schema(description = "Senha do administrador", example = "senhaSegura123")
    private String password;
    @Schema(description = "Indica se ele pode ler ou executar os endpoints", example = "true")
    private boolean write;
    @Schema(description = "URL da imagem do administrador", example = "https://example.com/imagem.jpg")
    private String imageAdmin;
}
