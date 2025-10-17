package com.api.dto.address;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

@Value
@Schema(description = "DTO de resposta para informações do endereço")
public class AddressResponseDTO {
    @Schema(description = "ID do endereço", example = "1")
    private long id;
    @Schema(description = "Estado do endereço", example = "SP")
    private String state;
    @Schema(description = "Cidade do endereço", example = "São Paulo")
    private String city;
    @Schema(description = "Logradouro do endereço", example = "Rua das Flores")
    private String publicPlace;
    @Schema(description = "CEP do endereço", example = "12345-678")
    private String cep;
    @Schema(description = "Bairro do endereço", example = "Jardim das Acácias")
    private String neighbourhood;
    @Schema(description = "Número da casa", example = "100A")
    private Integer houseNumber;
    @Schema(description = "Complemento do endereço", example = "Apto 101")
    private String complement;
}
