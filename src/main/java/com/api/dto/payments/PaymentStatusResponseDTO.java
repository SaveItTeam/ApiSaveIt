package com.api.dto.payments;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentStatusResponseDTO {
    private long enterpriseId;
    private String enterpriseName;
    private String planName;
    private Date paymentDate;
    private Double amount;
    private String status;
    private String paymentMethod;
}
