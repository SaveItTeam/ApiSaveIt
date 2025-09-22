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
    private String plan_id;
    private String email;
    private String phone_number;
    private long endereco_id;
    private String password;
    private String category;
    private boolean is_buyer;
    public Enterprise() {
    }

    public Enterprise(long id, String cnpj, String name, String plan_id, String email, String phone_number, long endereco_id, String password, String category, boolean is_buyer) {
        this.id = id;
        this.cnpj = cnpj;
        this.name = name;
        this.plan_id = plan_id;
        this.email = email;
        this.phone_number = phone_number;
        this.endereco_id = endereco_id;
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

    public long getEndereco_id() {
        return endereco_id;
    }

    public void setEndereco_id(long endereco_id) {
        this.endereco_id = endereco_id;
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
                ", endereco_id=" + endereco_id +
                ", senha='" + password + '\'' +
                ", categoria='" + category + '\'' +
                ", is_buyer=" + is_buyer +
                '}';
    }
}
