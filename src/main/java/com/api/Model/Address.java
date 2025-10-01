
package com.api.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private long id;
    private String state;
    private String city;
    private String public_place;
    private String cep;
    private String neighbourhood;
    private int house_number;
    private String complement;


    public Address() {
    }

    public Address(long id, String state, String public_place, String cep, String neighbourhood, String complement, String city, int house_number) {
        this.id = id;
        this.state = state;
        this.public_place = public_place;
        this.cep = cep;
        this.neighbourhood = neighbourhood;
        this.complement = complement;
        this.city = city;
        this.house_number = house_number;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }



    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getHouse_number() {
        return house_number;
    }
    public void setHouse_number(int house_number) {
        this.house_number = house_number;
    }

    @Override
    public String toString() {
        return "Endereco{" +
                "id=" + id +
                ", estado='" + state + '\'' +
                ", cidade='" + city + '\'' +
                ", rua='" + public_place + '\'' +
                ", cep='" + cep + '\'' +
                ", bairro='" + neighbourhood + '\'' +
                ", complemento='" + complement + '\'' +
                ", house_number='" + house_number + '\'' +
                '}';
    }
}