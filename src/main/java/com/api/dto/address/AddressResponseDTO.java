package com.api.dto.address;

import com.api.Model.Address;
import jakarta.persistence.Column;

public class AddressResponseDTO {
    private long id;
    private String nome;
    private String state;
    private String city;
    private String public_place;
    private String cep;
    private String neighborhood;
    private String complement;
    private String number;

    public AddressResponseDTO(long id, String nome, String state, String city, String public_place, String cep, String neighborhood, String complement, String number) {
        this.id = id;
        this.nome = nome;
        this.state = state;
        this.city = city;
        this.public_place = public_place;
        this.cep = cep;
        this.neighborhood = neighborhood;
        this.complement = complement;
        this.number = number;
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

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
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

    @Override
    public String toString() {
        return "AddressResponseDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", public_place='" + public_place + '\'' +
                ", cep='" + cep + '\'' +
                ", neighborhood='" + neighborhood + '\'' +
                ", complement='" + complement + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
