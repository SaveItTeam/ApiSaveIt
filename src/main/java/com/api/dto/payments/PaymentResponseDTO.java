package com.api.dto.payments;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

import java.util.Date;

@Value
@Schema(description = "Resposta com os dados de um pagamento")
public class PaymentResponseDTO {
    @Schema(description = "ID único do pagamento", example = "1")
    long id;
    @Schema(description = "ID da empresa associada ao pagamento", example = "10")
    long enterpriseId;
    @Schema(description = "ID do plano associado", example = "3")
    long planId;
    @Schema(description = "Data em que o pagamento foi realizado", example = "2025-10-17T19:00:00Z")
    Date paymentDate;
    @Schema(description = "Valor total do pagamento", example = "99.90")
    Double amount;
    @Schema(description = "Status atual do pagamento", example = "CONFIRMADO")
    String status;
    @Schema(description = "Método de pagamento utilizado", example = "CARTÃO DE CRÉDITO")
    String paymentMethod;
}
