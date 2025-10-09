package com.api.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Batch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "Campo vazio")
    @Column(unique = true)
    private long id;
    private String unit_measure;
    private Date entry_date;
    private String batch_code;
    private Date expiration_date;
    private int quantity_measure;
    private long product_id;
}
