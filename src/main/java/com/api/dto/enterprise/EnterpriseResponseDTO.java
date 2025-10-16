package com.api.dto.enterprise;

import lombok.Data;
import lombok.Value;

@Value
public class EnterpriseResponseDTO {
    private long id;
    private String cnpj;
    private String name;
    private int planId;
    private String email;
    private String enterpriseImage;
    private String phoneNumber;
    private long addressId;
    private String password;

}
