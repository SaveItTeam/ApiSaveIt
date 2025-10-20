
package com.api.model;

import jakarta.persistence.*;
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
    private String state;
    private String city;
    @Column(name = "public_place")
    private String publicPlace;
    private String cep;
    @Column(name = "neighbourhood")
    private String neighbourhood;
    @Column(name = "house_number")
    private Integer houseNumber;
    private String complement;
}