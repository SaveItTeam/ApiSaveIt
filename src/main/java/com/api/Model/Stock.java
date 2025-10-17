package com.api.Model;

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
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",unique = true)
    private long id;
    @Column(name = "quantity_input")
    private int quantity_input;
    @Column(name = "quantity_output")
    private int quantity_output;
    @Column(name = "batch_id")
    private int batch_id;
}
