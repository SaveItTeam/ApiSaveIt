package com.api.dto.address;

import com.api.Model.Address;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

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
    private String public_place;
    @Schema(description = "CEP do endereço", example = "12345-678")
    @Column(length = 20)
    private String cep;
    @Schema(description = "Bairro do endereço", example = "Jardim das Acácias")
    @Column(length = 100)
    private String neighbourhood;
    @Schema(description = "Número da casa", example = "100A")
    @Column(length = 10)
    @NotNull(message = "Número da casa não pode ser nulo")
    private int house_number;
    @Schema(description = "Complemento do endereço", example = "Apto 101")
    @Column(length = 100)
    private String complement;
    @Schema(description = "Número do endereço", example = "100")
    @Column(length = 10)
    private String number;


    public AddressRequestDTO() {
    }

//    public static Address toModel(AddressRequestDTO addressRequest) {
//        Address address = new Address();
//        address.setNome(addressRequest.getNome());
//        address.setState(addressRequest.getState());
//        address.setCity(addressRequest.getCity());
//        address.setPublic_place(addressRequest.getPublic_place());
//        address.setCep(addressRequest.getCep());
//        address.setNeighborhood(addressRequest.getNeighborhood());
//        address.setComplement(addressRequest.getComplement());
//        address.setNumber(addressRequest.getNumber());
//        return address;
//    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPublic_place() {
        return public_place;
    }

    public void setPublic_place(String public_place) {
        this.public_place = public_place;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNeighbourhood() {
        return neighbourhood;
    }

    public void setNeighbourhood(String neighbourhood) {
        this.neighbourhood = neighbourhood;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getHouse_number() {
        return house_number;
    }
    public void setHouse_number(int house_number) {
        this.house_number = house_number;
    }
}
