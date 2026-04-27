package com.andres.banking.domain.model;

import lombok.Getter;


/**
 *  Payment Status for Payment Order.
 */
@Getter
public enum PaymentStatus {

  INITIATED("INITIATED"),

  PROCESSING("PROCESSING"),

  COMPLETED("COMPLETED"),

  REJECTED("REJECTED");

  private final String value;

  PaymentStatus(String value) {
    this.value = value;
  }

}


