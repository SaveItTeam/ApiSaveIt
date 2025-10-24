package com.api.repository;

import com.api.dto.payments.PaymentStatusResponseDTO;
import com.api.model.Payments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PaymentRepository extends JpaRepository<Payments, Long> {

    @Query("""
        SELECT new com.api.dto.payments.PaymentStatusResponseDTO(
            e.id,
            e.name,
            p.name,
            pay.paymentDate,
            pay.amount,
            pay.status,
            pay.paymentMethod
        )
        FROM Payments pay
        JOIN pay.enterprise e
        JOIN pay.plan p
        ORDER BY pay.paymentDate DESC
    """)
    List<PaymentStatusResponseDTO> findAllPaymentsWithEnterpriseAndPlan();
}
