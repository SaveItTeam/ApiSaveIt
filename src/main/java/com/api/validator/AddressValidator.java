package com.api.validator;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AddressValidator {

    public void validate(Map<String, Object> updates) {
        if (updates.isEmpty()) {
            throw new IllegalArgumentException("Nenhum campo enviado para atualização.");
        }

        for (String key : updates.keySet()) {
            Object value = updates.get(key);

            switch (key) {
                case "city":
                case "state":
                case "publicPlace":
                case "neighbourhood":
                case "complement":
                    validateStringField(key, value);
                    break;

                case "cep":
                    validateCep(value);
                    break;

                case "houseNumber":
                    validateHouseNumber(value);
                    break;

                default:
                    throw new IllegalArgumentException("Campo '" + key + "' não pode ser atualizado.");
            }
        }
    }

    private void validateStringField(String field, Object value) {
        if (!(value instanceof String)) {
            throw new IllegalArgumentException("Campo '" + field + "' deve ser uma string.");
        }

        String text = ((String) value).trim();
        if (text.isEmpty()) {
            throw new IllegalArgumentException("Campo '" + field + "' não pode estar vazio.");
        }
    }

    private void validateCep(Object value) {
        if (!(value instanceof String)) {
            throw new IllegalArgumentException("Campo 'cep' deve ser uma string.");
        }

        String cep = ((String) value).trim();
        if (!cep.matches("\\d{5}-?\\d{3}")) { // aceita '12345-678' ou '12345678'
            throw new IllegalArgumentException("CEP inválido. Use o formato 00000-000.");
        }
    }

    private void validateHouseNumber(Object value) {
        if (!(value instanceof Number)) {
            throw new IllegalArgumentException("Campo 'houseNumber' deve ser numérico.");
        }

        int num = ((Number) value).intValue();
        if (num <= 0) {
            throw new IllegalArgumentException("O número da casa deve ser maior que zero.");
        }
    }
}

