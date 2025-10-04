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
    private String cnpj;
    private String name;
    private int plan_id;
    private String email;
    private String phone_number;
    private long address_id;
    private String password;
}
