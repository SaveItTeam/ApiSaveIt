package com.api.dto.enterprise;

import lombok.Data;
import lombok.Value;

@Value
public class EnterpriseResponseDTO {
    private long id;
    private String cnpj;
    private String name;
    private int plan_id;
    private String email;
    private String phone_number;
    private long address_id;
    private String password;

}
