package com.api.exception;

import com.api.Exception.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import com.api.Exception.InvalidCnpjException;
import com.api.Exception.InvalidCategoryException;
import com.api.Exception.InvalidExpirationDateException;
import com.api.Exception.InvalidHouseNumberException;
import com.api.Exception.InvalidUnitMeasureException;
import com.api.exception.InvalidQuantityException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalException {


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException
            (RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro de execução: " + ex.getMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, Object>> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", "Violação de integridade no banco de dados");

        String message = ex.getMostSpecificCause().getMessage();

        if (message.contains("enterprise_email_key")) {
            body.put("message", "O e-mail informado já está cadastrado.");
        } else if (message.contains("enterprise_cnpj_key")) {
            body.put("message", "O CNPJ informado já está cadastrado.");
        }else if (message.contains("product_enterprise_id_fkey")) {
            body.put("message", "A empresa com esse ID não existe.");
        }
        else {
            body.put("message", "Erro de integridade ao salvar o dado. Detalhes" + message);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException
            (IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Argumento inválido: " + ex.getMessage());
    }

    @ExceptionHandler(InvalidUnitMeasureException.class)
    public ResponseEntity<String> handleInvalidUnitMeasureException
            (InvalidQuantityException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Unidade de medida invalida: " + ex.getMessage());
    }

    @ExceptionHandler(InvalidCnpjException.class)
    public ResponseEntity<String> handleInvalidCnpjException
            (InvalidCnpjException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("CNPJ inválido: " + ex.getMessage());
    }

    @ExceptionHandler(InvalidHouseNumberException.class)
    public ResponseEntity<String> InvalidHouseNumberException
            (InvalidHouseNumberException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Número da casa tem que ser maior que zero. Detalhes do erro: " + ex.getMessage());
    }

    @ExceptionHandler(InvalidExpirationDateException.class)
    public ResponseEntity<String> handleInvalidExpirationDateException
            (InvalidExpirationDateException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Data de validade não pode ser menor ou igual a data de entrada. Detalhes do erro: " + ex.getMessage());
    }

    @ExceptionHandler(InvalidCategoryException.class)
    public ResponseEntity<String> handleInvalidCategoryException
            (InvalidCategoryException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Categoria inválida: " + ex.getMessage());
    }


    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException
            (NoSuchElementException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Elemento não encontrado: " + ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException
            (Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro inesperado " + ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handler(MethodArgumentNotValidException ex) {
        Map<String, String> erros = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            erros.put(error.getField(), error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(erros);
    }
    @ExceptionHandler(InvalidQuantityException.class)
    public ResponseEntity<Map<String, String>> handleInvalidDataException
            (com.api.exception.InvalidQuantityException ex) {
        return ResponseEntity.badRequest().body(ex.getErros());
    }

}