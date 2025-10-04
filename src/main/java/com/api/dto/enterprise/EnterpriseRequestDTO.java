package com.api.dto.enterprise;

import com.api.Model.Enterprise;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EnterpriseRequestDTO {
    @Column(length = 20)
    @NotNull(message = "Cnpj vazio")
    private String cnpj;
    @Column(length = 100)
    @NotNull(message = "Nome vazio")
    private String name;
    @Column(length = 50)
    @NotNull(message = "Codigo vazio")
    private int plan_id;
    @Column(length = 100)
    @NotNull(message = "Email vazio")
    private String email;
    @Column(length = 20)
    @NotNull(message = "Telefone vazio")
    private String phone_number;
    @NotNull(message = "Id do endereco vazio")
    private long address_id;
    @NotNull(message = "Senha vazia")
    @Column(length = 255)
    private String password;

}
