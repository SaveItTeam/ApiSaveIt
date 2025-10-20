package com.api.Controller;

import com.api.Exception.GlobalException;
import com.api.OpenAPI.PaymentOpenApi;
import com.api.Service.PaymentsService;
import com.api.dto.payments.PaymentRequestDTO;
import com.api.dto.payments.PaymentResponseDTO;
import com.api.validator.OnCreate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/payments")
public class PaymentController implements PaymentOpenApi {

    private final PaymentsService paymentsService;
    private final LocalValidatorFactoryBean defaultValidator;
    private GlobalException ge;

    @Autowired
    public PaymentController(PaymentsService paymentsService, LocalValidatorFactoryBean defaultValidator) {
        this.paymentsService = paymentsService;
        this.defaultValidator = defaultValidator;
    }

    @GetMapping("/selecionar")
    public ResponseEntity<List<PaymentResponseDTO>> listPayments() {
        List<PaymentResponseDTO> payments = paymentsService.listPayments();
        return ResponseEntity.ok(payments);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<PaymentResponseDTO> getPaymentById(@PathVariable Long id) {
        PaymentResponseDTO payment = paymentsService.getPaymentById(id);
        return ResponseEntity.ok(payment);
    }

    @PostMapping("/inserir")
    public ResponseEntity<?> insertPayment(@Validated({OnCreate.class, Default.class}) @RequestBody PaymentRequestDTO payment) {
        paymentsService.insertPayment(payment);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Pagamento inserido com sucesso!");
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<?> deletePayment(@PathVariable Long id) {
        paymentsService.deletePayment(id);
        return ResponseEntity.ok("Pagamento excluído com sucesso!");
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> updatePayment(@PathVariable Long id, @Valid @RequestBody PaymentRequestDTO paymentAtualizado) {
        PaymentResponseDTO paymentResponseDTO = paymentsService.updatePayment(id, paymentAtualizado);
        return ResponseEntity.ok(paymentResponseDTO);
    }

    @PatchMapping("/atualizarParcial/{id}")
    public ResponseEntity<?> updatePaymentPartial(@PathVariable Long id, @RequestParam Map<String, Object> updates) {
        PaymentResponseDTO paymentResponseDTO = paymentsService.updatePaymentPartial(id, updates);
        return ResponseEntity.ok(paymentResponseDTO);
    }

}
