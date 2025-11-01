package com.api.dto.enterprise;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EnterpriseRequestDTO {
    @Column(length = 20)
    @NotNull(message = "Cnpj vazio")
    @Schema(description = "CNPJ da empresa", example = "12.345.678/0001-90")
    private String cnpj;
    @Column(length = 100)
    @NotNull(message = "Nome vazio")
    @Schema(description = "Nome da empresa", example = "Empresa Exemplo Ltda")
    private String name;
    @Column(length = 50)
    @NotNull(message = "Codigo vazio")
    @Schema(description = "Código da empresa", example = "EMP12345")
    private long planId;
    @Column(length = 100, unique = true)
    @NotNull(message = "Email vazio")
    @Schema(description = "Email da empresa", example = "empresa@gmail.com")
    private String email;
    @Column(length = 100)
    @NotNull(message = "Imagem vazia")
    @Schema(description = "Imagem da empresa", example = "https://....")
    private String enterpriseImage;
    @Column(length = 20)
    @NotNull(message = "Telefone vazio")
    @Schema(description = "Telefone da empresa", example = "11987654321")
    private String phoneNumber;
    @NotNull(message = "Id do endereco vazio")
    @Schema(description = "ID do endereço da empresa", example = "1")
    private long addressId;
    @NotNull(message = "Senha vazia")
    @Column(length = 255)
    @Schema(description = "Senha da empresa", example = "senhaSegura123")
    private String password;

}
