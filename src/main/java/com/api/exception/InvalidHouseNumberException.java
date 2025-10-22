package com.api.Exception;

import java.util.Map;

public class InvalidHouseNumberException extends RuntimeException {
  public InvalidHouseNumberException(String message) {
    super(message);
  }
}
