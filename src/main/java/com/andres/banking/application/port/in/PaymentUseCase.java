package com.andres.banking.application.port.in;


import com.andres.banking.domain.model.PaymentOrder;

/**
 *  Interface for Uses Cases.
 */
public interface PaymentUseCase {

  PaymentOrder create(PaymentOrder request);

  PaymentOrder get(String id);

  PaymentOrder getStatus(String id);
}
