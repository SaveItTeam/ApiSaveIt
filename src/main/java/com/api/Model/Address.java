
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
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private long id;
    @Column(name = "state")
    private String state;
    @Column(name = "city")
    private String city;
    @Column(name = "public_place")
    private String publicPlace;
    @Column(name = "cep")
    private String cep;
    @Column(name = "neighbourhood")
    private String neighbourhood;
    @Column(name = "house_number")
    private int houseNumber;
    @Column(name = "complement")
    private String complement;
}