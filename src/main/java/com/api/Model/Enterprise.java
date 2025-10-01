package com.api.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Enterprise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "Campo vazio")
    @Column(unique = true)
    private long id;
    private String cnpj;
    private String name;
    private int plan_id;
    private String email;
    private String phone_number;
    private long address_id;
    private String password;

    public Enterprise() {
    }

    public Enterprise(long id, String cnpj, String name, int plan_id, String email, String phone_number, long address_id, String password) {
        this.id = id;
        this.cnpj = cnpj;
        this.name = name;
        this.plan_id = plan_id;
        this.email = email;
        this.phone_number = phone_number;
        this.address_id = address_id;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPlan_id() {
        return plan_id;
    }

    public void setPlan_id(int plan_id) {
        this.plan_id = plan_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public long getAddress_id() {
        return address_id;
    }

    public void setAddress_id(long address_id) {
        this.address_id = address_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "Empresa{" +
                "id=" + id +
                ", cnpj='" + cnpj + '\'' +
                ", nome='" + name + '\'' +
                ", codigo='" + plan_id + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + phone_number + '\'' +
                ", endereco_id=" + address_id +
                ", senha='" + password + '\'' +
                '}';
    }
}
