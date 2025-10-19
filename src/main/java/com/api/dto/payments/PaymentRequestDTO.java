package com.api.dto.payments;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Value;

import java.util.Date;

@Value
@Schema(description = "Requisição para registrar um novo pagamento")
public class PaymentRequestDTO {
    @NotNull(message = "O ID da empresa é obrigatório.")
    @Schema(description = "ID da empresa que realizou o pagamento", example = "10", required = true)
    long enterpriseId;
    @NotNull(message = "O ID do plano é obrigatório.")
    @Schema(description = "ID do plano adquirido", example = "3", required = true)
    long planId;
    @NotNull(message = "A data do pagamento é obrigatória.")
    @Schema(description = "Data em que o pagamento foi realizado", example = "2025-10-17T19:00:00Z", required = true)
    Date paymentDate;
    @NotNull(message = "O valor é obrigatório.")
    @Positive(message = "O valor deve ser positivo.")
    @Schema(description = "Valor total do pagamento", example = "99.90", required = true)
    Double amount;
    @NotBlank(message = "O status do pagamento é obrigatório.")
    @Schema(description = "Status atual do pagamento", example = "Ativo/Inativo", required = true)
    String status;
    @NotBlank(message = "A forma de pagamento é obrigatória.")
    @Schema(description = "Método de pagamento utilizado", example = "PIX", required = true)
    String paymentMethod;
}
