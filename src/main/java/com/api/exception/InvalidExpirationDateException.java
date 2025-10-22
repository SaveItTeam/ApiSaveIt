package com.api.Exception;

import java.util.Map;

public class InvalidExpirationDateException extends RuntimeException {
    public InvalidExpirationDateException(String message) {
        super(message);
    }
}
