package com.api.validator;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.regex.Pattern;

@Component
public class EmployeeValidator {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w.-]+@[\\w.-]+\\.[a-z]{2,}$");

    public void validate(Map<String , Object> updates) {
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

        if (updates.containsKey("password")) {
            String password = (String) updates.get("password");
            if (password == null || password.length() < 6) {
                throw new IllegalArgumentException("Senha deve ter pelo menos 6 caracteres.");
            }
        }
    }
}
