package com.api.dto.enterprise;

import com.api.Model.Enterprise;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;

public class EnterpriseRequestDTO {
    @Column(length = 20)
    @NotNull(message = "Cnpj vazio")
    private String cnpj;
    @Column(length = 100)
    @NotNull(message = "Nome vazio")
    private String name;
    @Column(length = 50)
    @NotNull(message = "Codigo vazio")
    private int plan_id;
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
    public EnterpriseRequestDTO() {
    }

    public static Enterprise toModel(EnterpriseRequestDTO enterprise) {
        Enterprise enterpriseModel = new Enterprise();
        enterpriseModel.setName(enterprise.getName());
        enterpriseModel.setEmail(enterprise.getEmail());
        enterpriseModel.setPassword(enterprise.getPassword());
        enterpriseModel.setPhone_number(enterprise.getPhone_number());
        enterpriseModel.setAddress_id(enterprise.getAddress_id());
        enterpriseModel.setCategory(enterprise.getCategory());
        return enterpriseModel;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPlan_id() {
        return plan_id;
    }

    public void setPlan_id(int plan_id) {
        this.plan_id = plan_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
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

}
