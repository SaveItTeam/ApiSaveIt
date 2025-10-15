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
    @Column(name = "id",unique = true)
    private long id;
    @Column(name = "unit_measure")
    private String unitMeasure;
    @Column(name = "entry_date")
    private Date entryDate;
    @Column(name = "batch_code")
    private String batchCode;
    @Column(name = "expiration_date")
    private Date expirationDate;
    @Column(name = "quantity_measure")
    private int quantityMeasure;
    @Column(name = "product_id")
    private long productId;
}
