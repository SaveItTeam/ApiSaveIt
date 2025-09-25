package com.api.dto.address;

import com.api.Model.Address;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;

public class AddressRequestDTO {
    @Column(length = 100)
    private String nome;
    @Column(length = 50)
    private String state;
    @Column(length = 100)
    private String city;
    @Column(length = 100)
    private String public_place;
    @Column(length = 20)
    private String cep;
    @Column(length = 100)
    private String neighbourhood;
    @Column(length = 100)
    private String complement;
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
}
