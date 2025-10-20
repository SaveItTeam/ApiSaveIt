package com.api.validator;

import com.api.exception.InvalidQuantityException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ProductPatchValidator {
    public static void validarNome(String nome) {
        if (nome == null || nome.length() < 2) {
            throw new IllegalArgumentException("Menor que 2 caracteres. Deve conter mais que 2 caracteres");
        }
    }
    public static void validarIdEmpresa(String enterprise_id) {
        if (enterprise_id == null) {
            throw new IllegalArgumentException("O valor de empresa não pode estar nulo");
        }
    }
    public static void validarMarca(String marca) {
        if (marca == null || marca.length() < 2) {
            throw new IllegalArgumentException("Menor que 2 caracteres. Deve conter mais que 2 caracteres");
        }
    }

    public static void validarCamposParciais(Map<String, Object> updates) {
        Map<String, String> erros = new HashMap<>();

        if (updates.containsKey("name")) {
            String nome = (String) updates.get("name");
            if (nome == null || nome.length() < 2) {
                erros.put("nome", "Menor que 2 caracteres. Deve conter mais que 2 caracteres");
            }
        }

        if (updates.containsKey("brand")) {
            String marca = (String) updates.get("brand");
            if (marca == null || marca.length() < 2) {
                erros.put("brand", "Menor que 2 caracteres. Deve conter mais que 2 caracteres");
            }
        }

        if (!erros.isEmpty()) {
            throw new InvalidQuantityException(erros);
        }
    }
}
