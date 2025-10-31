package com.api.service;

import com.api.dto.payments.PaymentRequestDTO;
import com.api.dto.payments.PaymentResponseDTO;
import com.api.dto.payments.PaymentStatusResponseDTO;
import com.api.model.Enterprise;
import com.api.model.Payments;
import com.api.model.Plan;
import com.api.repository.EnterpriseRepository;
import com.api.repository.PaymentRepository;
import com.api.repository.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PaymentsService {

    private final PaymentRepository paymentRepository;
    private final EnterpriseRepository enterpriseRepository;
    private final PlanRepository planRepository;

    @Autowired
    public PaymentsService(PaymentRepository paymentRepository, EnterpriseRepository enterpriseRepository, PlanRepository planRepository) {
        this.paymentRepository = paymentRepository;
        this.enterpriseRepository = enterpriseRepository;
        this.planRepository = planRepository;
    }

    private PaymentResponseDTO mapToDTO(Payments payment) {
        long enterpriseId = payment.getEnterprise() != null ? payment.getEnterprise().getId() : 0;
        long planId = payment.getPlan() != null ? payment.getPlan().getId() : 0;

        return new PaymentResponseDTO(
                payment.getId(),
                enterpriseId,
                planId,
                payment.getPaymentDate(),
                payment.getAmount(),
                payment.getStatus(),
                payment.getPaymentMethod()
        );
    }

    public List<PaymentResponseDTO> listPayments() {
        List<Payments> payments = paymentRepository.findAll();
        List<PaymentResponseDTO> responseList = new ArrayList<>();
        for (Payments p : payments) {
            responseList.add(mapToDTO(p));
        }
        return responseList;
    }

    public List<PaymentStatusResponseDTO> listDetailedPayments() {
        return paymentRepository.findAllPaymentsWithEnterpriseAndPlan();
    }

    public PaymentResponseDTO getPaymentById(Long id) {
        Payments payment = paymentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Pagamento com ID " + id + " não encontrado"));
        return mapToDTO(payment);
    }

    public void insertPayment(PaymentRequestDTO dto) {
        Payments payment = new Payments();

        Enterprise enterprise = enterpriseRepository.findById(dto.getEnterpriseId())
                .orElseThrow(() -> new NoSuchElementException("Empresa não encontrada"));
        Plan plan = planRepository.findById(dto.getPlanId())
                .orElseThrow(() -> new NoSuchElementException("Plano não encontrado"));

        payment.setEnterprise(enterprise);
        payment.setPlan(plan);
        payment.setPaymentDate(dto.getPaymentDate());
        payment.setAmount(dto.getAmount());
        payment.setStatus(dto.getStatus());
        payment.setPaymentMethod(dto.getPaymentMethod());

        paymentRepository.save(payment);
    }

    public void deletePayment(Long id) {
        if (!paymentRepository.existsById(id)) {
            throw new NoSuchElementException("Pagamento com ID " + id + " não encontrado");
        }
        paymentRepository.deleteById(id);
    }

    public PaymentResponseDTO updatePayment(Long id, PaymentRequestDTO dto) {
        Payments payment = paymentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Pagamento com ID " + id + " não encontrado"));

        Enterprise enterprise = enterpriseRepository.findById(dto.getEnterpriseId())
                .orElseThrow(() -> new NoSuchElementException("Empresa não encontrada"));
        Plan plan = planRepository.findById(dto.getPlanId())
                .orElseThrow(() -> new NoSuchElementException("Plano não encontrado"));

        payment.setEnterprise(enterprise);
        payment.setPlan(plan);
        payment.setPaymentDate(dto.getPaymentDate());
        payment.setAmount(dto.getAmount());
        payment.setStatus(dto.getStatus());
        payment.setPaymentMethod(dto.getPaymentMethod());

        paymentRepository.save(payment);
        return mapToDTO(payment);
    }

    public PaymentResponseDTO updatePaymentPartial(Long id, Map<String, Object> updates) {
        Payments payment = paymentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Pagamento com ID " + id + " não encontrado"));

        if (updates.containsKey("enterpriseId")) {
            Long enterpriseId = Long.parseLong(updates.get("enterpriseId").toString());
            Enterprise enterprise = enterpriseRepository.findById(enterpriseId)
                    .orElseThrow(() -> new NoSuchElementException("Empresa não encontrada"));
            payment.setEnterprise(enterprise);
        }

        if (updates.containsKey("planId")) {
            Long planId = Long.parseLong(updates.get("planId").toString());
            Plan plan = planRepository.findById(planId)
                    .orElseThrow(() -> new NoSuchElementException("Plano não encontrado"));
            payment.setPlan(plan);
        }

        if (updates.containsKey("paymentDate")) {
            payment.setPaymentDate(new Date());
        }

        if (updates.containsKey("amount")) {
            payment.setAmount(Double.parseDouble(updates.get("amount").toString()));
        }

        if (updates.containsKey("status")) {
            payment.setStatus(updates.get("status").toString());
        }

        if (updates.containsKey("paymentMethod")) {
            payment.setPaymentMethod(updates.get("paymentMethod").toString());
        }

        paymentRepository.save(payment);
        return mapToDTO(payment);
    }
}
