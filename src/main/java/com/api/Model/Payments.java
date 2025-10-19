package com.api.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
public class Payments {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private long id;
    @Column(name = "enterprise_id")
    private long enterpriseId;
    @Column(name = "plan_id")
    private long planId;
    @Column(name = "payment_date")
    private Date paymentDate;
    private Double amount;
    private String status;
    @Column(name = "paymentMethod")
    private String paymentMethod;
}
