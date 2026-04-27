package com.andres.banking.application.exception;

/**
 * Exception thrown when payment order is not found.
 */
public class PaymentNotFoundException extends RuntimeException {

  public PaymentNotFoundException(String message) {
    super(message);
  }
}
