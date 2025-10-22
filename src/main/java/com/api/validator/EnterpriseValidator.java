package com.api.validator;

import com.api.Exception.InvalidCnpjException;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.regex.Pattern;

@Component
public class EnterpriseValidator {
    private static final Pattern CNPJ_PATTERN = Pattern.compile("\\d{14}");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w.-]+@[\\w.-]+\\.[a-z]{2,}$");

    public void validate(Map<String, Object> updates) {
        if (updates.containsKey("cnpj")) {
            String cnpj = (String) updates.get("cnpj");
            if (cnpj == null || !CNPJ_PATTERN.matcher(cnpj).matches()) {
                throw new InvalidCnpjException("CNPJ inválido. Deve conter 14 dígitos numéricos.");
            }
        }

        if (updates.containsKey("email")) {
            String email = (String) updates.get("email");
            if (email == null || !EMAIL_PATTERN.matcher(email).matches()) {
                throw new IllegalArgumentException("E-mail inválido.");
            }
        }

        if (updates.containsKey("planId")) {
            Object planId = updates.get("planId");
            if (!(planId instanceof Number) || ((Number) planId).intValue() <= 0) {
                throw new IllegalArgumentException("Plan ID deve ser um número positivo.");
            }
        }

        if (updates.containsKey("phoneNumber")) {
            String phone = (String) updates.get("phoneNumber");
            if (phone == null || phone.length() < 8) {
                throw new IllegalArgumentException("Telefone inválido.");
            }
        }

        if (updates.containsKey("password")) {
            String password = (String) updates.get("password");
            if (password == null || password.length() < 6) {
                throw new IllegalArgumentException("Senha deve ter pelo menos 6 caracteres.");
            }
        }
    }
}
