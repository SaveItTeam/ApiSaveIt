package com.api.dto.enterprise;

public class EnterpriseResponseDTO {
    private long id;
    private String cnpj;
    private String name;
    private String plan_id;
    private String email;
    private String phone_number;
    private long address_id;
    private String password;
    private String category;
    private boolean is_buyer;

    public EnterpriseResponseDTO(long id, String cnpj, String name, String plan_id, String email, String phone_number, long address_id, String password, String category, boolean is_buyer) {
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
        return "EnterpriseResponseDTO{" +
                "id=" + id +
                ", cnpj='" + cnpj + '\'' +
                ", name='" + name + '\'' +
                ", plan_id='" + plan_id + '\'' +
                ", email='" + email + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", address_id=" + address_id +
                ", password='" + password + '\'' +
                ", category='" + category + '\'' +
                ", is_buyer=" + is_buyer +
                '}';
    }
}
