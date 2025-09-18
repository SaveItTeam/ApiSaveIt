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
    private String plan_id;
    @Column(length = 100)
    @NotNull(message = "Email vazio")
    private String email;
    @Column(length = 20)
    @NotNull(message = "Telefone vazio")
    private String phone_number;
    @NotNull(message = "Id do endereco vazio")
    private long endereco_id;
    @NotNull(message = "Senha vazia")
    @Column(length = 255)
    private String password;
    @NotNull(message = "Categoria vazia")
    @Column(length = 100)
    private String category;
    @NotNull(message = "is buyer vazio")
    private boolean is_buyer;

    public EnterpriseRequestDTO() {
    }

    public static Enterprise toModel(EnterpriseRequestDTO enterprise) {
        Enterprise enterpriseModel = new Enterprise();
        enterpriseModel.setName(enterprise.getName());
        enterpriseModel.setEmail(enterprise.getEmail());
        enterpriseModel.setPassword(enterprise.getPassword());
        enterpriseModel.setPhone_number(enterprise.getPhone_number());
        enterpriseModel.setEndereco_id(enterprise.getEndereco_id());
        enterpriseModel.setCategory(enterprise.getCategory());
        enterpriseModel.setIs_buyer(enterprise.isIs_buyer());
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

    public String getPlan_id() {
        return plan_id;
    }

    public void setPlan_id(String plan_id) {
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
}
