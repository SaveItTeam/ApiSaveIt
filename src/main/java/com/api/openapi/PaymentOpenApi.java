package com.api.openapi;

import com.api.dto.payments.PaymentRequestDTO;
import com.api.dto.payments.PaymentResponseDTO;
import com.api.dto.payments.PaymentStatusResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

public interface PaymentOpenApi {

    @Operation(summary = "Listar todos os pagamentos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de pagamentos retornada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<List<PaymentResponseDTO>> listPayments();


    @Operation(summary = "Buscar um pagamento pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pagamento encontrado"),
            @ApiResponse(responseCode = "404", description = "Pagamento não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<PaymentResponseDTO> getPaymentById(@PathVariable Long id);


    @Operation(summary = "Inserir um novo pagamento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pagamento inserido com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<?> insertPayment(PaymentRequestDTO payment);


    @Operation(summary = "Excluir um pagamento pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pagamento excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pagamento não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<?> deletePayment(Long id);

    @Operation(summary = "Atualizar um pagamento pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pagamento atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "404", description = "Pagamento não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<?> updatePayment(Long id, PaymentRequestDTO paymentAtualizado);


    @Operation(summary = "Atualizar parcialmente um pagamento pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pagamento atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "404", description = "Pagamento não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<?> updatePaymentPartial(Long id, Map<String, Object> updates);

    @Operation(summary = "Obter detalhes dos pagamentos com status")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Detalhes dos pagamentos retornados com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    List<PaymentStatusResponseDTO> getDetailedPayments();
}
