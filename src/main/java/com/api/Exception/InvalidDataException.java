package com.api.Exception;

import java.util.Map;

public class InvalidDataException extends RuntimeException{
    private final Map<String, String> erros;

    public InvalidDataException(Map<String, String> erros){
        this.erros = erros;
    }

    public Map<String, String> getErros(){
        return erros;
    }
}
