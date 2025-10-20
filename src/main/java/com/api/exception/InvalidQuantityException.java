package com.api.exception;

import java.util.Map;

public class InvalidQuantityException extends RuntimeException{
    private final Map<String, String> erros;

    public InvalidQuantityException(Map<String, String> erros){
        this.erros = erros;
    }

    public Map<String, String> getErros(){
        return erros;
    }
}
