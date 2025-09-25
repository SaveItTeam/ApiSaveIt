package com.api.dto.enterprise;


import com.api.dto.address.AddressRequestDTO;

public class EnterpriseInsertDTO {
    private EnterpriseRequestDTO enterprise;
    private AddressRequestDTO address;

    public EnterpriseInsertDTO(EnterpriseRequestDTO enterprise, AddressRequestDTO address) {
        this.enterprise = enterprise;
        this.address = address;
    }

    public EnterpriseRequestDTO getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(EnterpriseRequestDTO enterprise) {
        this.enterprise = enterprise;
    }

    public AddressRequestDTO getAddress() {
        return address;
    }

    public void setAddress(AddressRequestDTO address) {
        this.address = address;
    }
}
