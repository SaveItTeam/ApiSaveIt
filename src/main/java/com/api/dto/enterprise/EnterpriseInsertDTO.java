package com.api.dto.enterprise;


import com.api.dto.address.AddressRequestDTO;
import jakarta.validation.Valid;
import lombok.Data;

@Data
public class EnterpriseInsertDTO {
    @Valid
    private EnterpriseRequestDTO enterprise;
    @Valid
    private AddressRequestDTO address;


}
