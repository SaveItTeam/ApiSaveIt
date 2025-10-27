package com.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Showcase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private long id;
    private String description;

    @ManyToOne
    @JoinColumn(name = "batch_id", nullable = false)
    private Batch batch;
    @Column(name = "quantity_showcase")
    private Integer quantityShowcase;
    @Column(name = "entrance_date")
    private Date entranceDate;

}
