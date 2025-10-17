package com.api.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Enterprise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "Campo vazio")
    @Column(unique = true)
    private long id;
    @Column(unique = true)
    private String cnpj;
    private String name;
    @Column(name = "plan_id")
    private long planId;
    @Column(unique = true)
    private String email;
    @Column(name = "enterprise_image")
    private String enterpriseImage;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "address_id")
    private long addressId;
    private String password;
}
