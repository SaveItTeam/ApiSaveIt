package com.api.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

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
    private Integer quantityInput;
    @Column(name = "quantity_output")
    private Integer quantityOutput;
    @Column(name = "batch_id")
    private long batchId;
    @Column(name = "product_id")
    private long productId;
    @Column(name = "discard_quantity")
    private Integer discardQuantity;
    @Column(name = "discard_reason")
    private String discardReason;
    @Column(name = "created_at")
    private LocalDateTime createdAt;

}
