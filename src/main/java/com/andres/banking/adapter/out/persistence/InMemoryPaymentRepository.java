package com.andres.banking.adapter.out.persistence;

import com.andres.banking.application.port.out.PaymentRepository;
import com.andres.banking.domain.model.PaymentOrder;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;


/**
 *  In memory repository for test.
 */
@Repository
public class InMemoryPaymentRepository implements PaymentRepository {


  private final Map<String, PaymentOrder> db = new ConcurrentHashMap<>();


  @Override
  public PaymentOrder save(PaymentOrder order) {
    db.put(order.getPaymentOrderId(), order);
    return order;
  }

  @Override
  public Optional<PaymentOrder> findById(String id) {
    return Optional.ofNullable(db.get(id));
  }
}
