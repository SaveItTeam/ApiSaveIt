
package com.api.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "Campo vazio")
    @Column(unique = true)
    private long id;
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
    private String neighborhood;
    @Column(length = 100)
    private String complement;
    @Column(length = 255)
    private String logradouro;
    @Column(length = 10)
    private String number;

    public Address() {
    }

    public Address(long id, String nome, String state, String public_place, String cep, String neighborhood, String complement, String logradouro, String number) {
        this.id = id;
        this.nome = nome;
        this.state = state;
        this.public_place = public_place;
        this.cep = cep;
        this.neighborhood = neighborhood;
        this.complement = complement;
        this.logradouro = logradouro;
        this.number = number;
    }

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Endereco{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", estado='" + state + '\'' +
                ", cidade='" + city + '\'' +
                ", rua='" + public_place + '\'' +
                ", cep='" + cep + '\'' +
                ", bairro='" + neighborhood + '\'' +
                ", complemento='" + complement + '\'' +
                ", logradouro='" + logradouro + '\'' +
                ", numero='" + number + '\'' +
                '}';
    }
}