package com.api.dto.address;

import com.api.Model.Address;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;

@Schema(description = "DTO de resposta para informações do endereço")
public class AddressResponseDTO {
    @Schema(description = "ID do endereço", example = "1")
    private long id;
    @Schema(description = "Nome do endereço", example = "Casa")
    private String nome;
    @Schema(description = "Estado do endereço", example = "SP")
    private String state;
    @Schema(description = "Cidade do endereço", example = "São Paulo")
    private String city;
    @Schema(description = "Logradouro do endereço", example = "Rua das Flores")
    private String public_place;
    @Schema(description = "CEP do endereço", example = "12345-678")
    private String cep;
    @Schema(description = "Bairro do endereço", example = "Jardim das Acácias")
    private String neighbourhood;
    @Schema(description = "Número da casa", example = "100A")
    private int house_number;
    @Schema(description = "Complemento do endereço", example = "Apto 101")
    private String complement;

    public AddressResponseDTO(long id, String nome, String state, String city, String public_place, String cep, String neighbourhood, String complement, int house_number) {
        this.id = id;
        this.nome = nome;
        this.state = state;
        this.city = city;
        this.public_place = public_place;
        this.cep = cep;
        this.neighbourhood = neighbourhood;
        this.complement = complement;
        this.house_number = house_number;
    }

//    public static AddressResponseDTO toDTO(Address address) {
//        return new AddressResponseDTO(address.getId(),address.getNome(),address.getState(),address.getCity(),address.getPublic_place(),address.getCep(),address.getNeighborhood(),address.getComplement(),address.getNumber());
//    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public int getHouse_number() {
        return house_number;
    }
    public void setHouse_number(int house_number) {
        this.house_number = house_number;
    }
    @Override
    public String toString() {
        return "AddressResponseDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", public_place='" + public_place + '\'' +
                ", cep='" + cep + '\'' +
                ", neighbourhood='" + neighbourhood + '\'' +
                ", complement='" + complement + '\'' +
                '}';
    }
}
