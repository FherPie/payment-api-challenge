package com.andres.banking.domain.service;

import com.andres.banking.application.port.in.PaymentUseCase;
import com.andres.banking.application.port.out.PaymentRepository;
import com.andres.banking.application.exception.PaymentNotFoundException;
import com.andres.banking.domain.model.PaymentOrder;

import java.time.OffsetDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;


/**
 * Service logic for Payment Use Case.
 *
 */
@Service
public class PaymentService implements PaymentUseCase {


  public final PaymentRepository paymentRepository;

  public PaymentService(PaymentRepository paymentRepository) {
    this.paymentRepository = paymentRepository;
  }

  @Override
  public PaymentOrder create(PaymentOrder request) {
    request.setPaymentOrderId(UUID.randomUUID().toString());
    request.setLastUpdate(OffsetDateTime.now());
    return paymentRepository.save(request);
  }

  @Override
  public PaymentOrder get(String id) {
    return paymentRepository.findById(id).orElseThrow(() -> new PaymentNotFoundException("Payment" +
        " order not found: " + id));
  }

  @Override
  public PaymentOrder getStatus(String id) {
    return paymentRepository.findById(id).orElseThrow(() -> new PaymentNotFoundException("Payment" +
        " order not found: " + id));
  }
}
