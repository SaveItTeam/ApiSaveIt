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
    @Column(name = "id",unique = true)
    private long id;
    @Column(name = "cnpj", unique = true)
    private String cnpj;
    @Column(name = "name")
    private String name;
    @Column(name = "plan_id")
    private int planId;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "enterprise_image")
    private String enterpriseImage;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "address_id")
    private long addressId;
    @Column(name = "password")
    private String password;
}
