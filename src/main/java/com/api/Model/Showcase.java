package com.api.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    private double price;
    private long batch_id;
    private Date entrance_date;
}
