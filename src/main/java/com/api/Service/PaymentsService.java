package com.api.Service;

import com.api.Model.Payments;
import com.api.Repository.PaymentRepository;
import com.api.dto.payments.PaymentRequestDTO;
import com.api.dto.payments.PaymentResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PaymentsService {

    private final PaymentRepository paymentRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public PaymentsService(PaymentRepository paymentRepository, ObjectMapper objectMapper) {
        this.paymentRepository = paymentRepository;
        this.objectMapper = objectMapper;
    }

    public List<PaymentResponseDTO> listPayments() {
        List<Payments> payments = paymentRepository.findAll();
        List<PaymentResponseDTO> responseList = new ArrayList<>();
        for (Payments p : payments) {
            responseList.add(objectMapper.convertValue(p, PaymentResponseDTO.class));
        }
        return responseList;
    }

    public PaymentResponseDTO getPaymentById(Long id) {
        Payments payment = paymentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Pagamento com ID " + id + " não encontrado"));
        return objectMapper.convertValue(payment, PaymentResponseDTO.class);
    }

    public void insertPayment(PaymentRequestDTO paymentRequest) {
        Payments payment = objectMapper.convertValue(paymentRequest, Payments.class);
        paymentRepository.save(payment);
    }

    public void deletePayment(Long id) {
        if (!paymentRepository.existsById(id)) {
            throw new NoSuchElementException("Pagamento com ID " + id + " não encontrado");
        }
        paymentRepository.deleteById(id);
    }

    public PaymentResponseDTO updatePayment(Long id, PaymentRequestDTO paymentAtualizado) {
        Payments payment = paymentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Pagamento com ID " + id + " não encontrado"));

        payment.setEnterpriseId(paymentAtualizado.getEnterpriseId());
        payment.setPlanId(paymentAtualizado.getPlanId());
        payment.setPaymentDate(paymentAtualizado.getPaymentDate());
        payment.setAmount(paymentAtualizado.getAmount());
        payment.setStatus(paymentAtualizado.getStatus());
        payment.setPaymentMethod(paymentAtualizado.getPaymentMethod());

        paymentRepository.save(payment);
        return objectMapper.convertValue(payment, PaymentResponseDTO.class);
    }

    public PaymentResponseDTO updatePaymentPartial(Long id, Map<String, Object> updates) {
        Payments payment = paymentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Pagamento com ID " + id + " não encontrado"));

        if (updates.containsKey("enterpriseId")) {
            payment.setEnterpriseId(Long.parseLong(updates.get("enterpriseId").toString()));
        }
        if (updates.containsKey("planId")) {
            payment.setPlanId(Long.parseLong(updates.get("planId").toString()));
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
        return objectMapper.convertValue(payment, PaymentResponseDTO.class);
    }
}
