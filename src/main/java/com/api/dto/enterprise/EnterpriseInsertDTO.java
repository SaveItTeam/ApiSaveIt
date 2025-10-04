package com.api.dto.enterprise;


import com.api.dto.address.AddressRequestDTO;
import lombok.Data;

@Data
public class EnterpriseInsertDTO {
    private EnterpriseRequestDTO enterprise;
    private AddressRequestDTO address;


}
