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
    @Column(length = 20)
    @NotNull(message = "Cnpj vazio")
    private String cnpj;
    @Column(length = 100)
    @NotNull(message = "Nome vazio")
    private String name;
    @Column(length = 50)
    @NotNull(message = "Codigo vazio")
    private String plan_id;
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
    @NotNull(message = "Categoria vazia")
    @Column(length = 100)
    private String category;
    @NotNull(message = "is buyer vazio")
    private boolean is_buyer;
    public Enterprise() {
    }

    public Enterprise(long id, String cnpj, String name, String plan_id, String email, String phone_number, long address_id, String password, String category, boolean is_buyer) {
        this.id = id;
        this.cnpj = cnpj;
        this.name = name;
        this.plan_id = plan_id;
        this.email = email;
        this.phone_number = phone_number;
        this.address_id = address_id;
        this.password = password;
        this.category = category;
        this.is_buyer = is_buyer;
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

    public String getPlan_id() {
        return plan_id;
    }

    public void setPlan_id(String plan_id) {
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isIs_buyer() {
        return is_buyer;
    }

    public void setIs_buyer(boolean is_buyer) {
        this.is_buyer = is_buyer;
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
                ", categoria='" + category + '\'' +
                ", is_buyer=" + is_buyer +
                '}';
    }
}
