package com.andres.banking.application.port.out;

import com.andres.banking.domain.model.PaymentOrder;
import java.util.Optional;

/**
 * Output port for payment order persistence operations.
 */
public interface PaymentRepository {

  /**
   * Saves a payment order.
   *
   * @param order payment order
   * @return saved payment order
   */
  PaymentOrder save(PaymentOrder order);

  /**
   * Finds a payment order by identifier.
   *
   * @param id payment order identifier
   * @return optional payment order
   */
  Optional<PaymentOrder> findById(String id);
}