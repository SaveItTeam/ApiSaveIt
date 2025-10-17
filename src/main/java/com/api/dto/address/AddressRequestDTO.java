package com.api.dto.address;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class AddressRequestDTO {
    @Column(length = 100)
    @Schema(description = "Nome do endereço", example = "Casa")
    private String nome;
    @Schema(description = "Estado do endereço", example = "SP")
    @Column(length = 50)
    private String state;
    @Schema(description = "Cidade do endereço", example = "São Paulo")
    @Column(length = 100)
    private String city;
    @Schema(description = "Logradouro do endereço", example = "Rua das Flores")
    @Column(length = 100)
    private String publicPlace;
    @Schema(description = "CEP do endereço", example = "12345-678")
    @Column(length = 20)
    private String cep;
    @Schema(description = "Bairro do endereço", example = "Jardim das Acácias")
    @Column(length = 100)
    private String neighbourhood;
    @Schema(description = "Número da casa", example = "100A")
    @Column(length = 10)
    @NotNull(message = "Número da casa não pode ser nulo")
    private int houseNumber;
    @Schema(description = "Complemento do endereço", example = "Apto 101")
    @Column(length = 100)
    private String complement;
}
