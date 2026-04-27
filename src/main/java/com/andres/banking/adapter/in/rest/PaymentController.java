package com.andres.banking.adapter.in.rest;

import com.andres.banking.adapter.in.rest.mapper.PaymentOrderMapper;
import com.andres.banking.application.port.in.PaymentUseCase;
import com.andres.banking.domain.model.PaymentOrder;
import com.andres.generated.api.PaymentInitiationApi;
import com.andres.generated.dto.CreatePaymentOrderRequest;
import com.andres.generated.dto.CreatePaymentOrderResponse;
import com.andres.generated.dto.PaymentStatusResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for payment initiation operations.
 *
 * @author Andrés
 */
@RestController
public class PaymentController implements PaymentInitiationApi {

  private final PaymentUseCase useCase;
  private final PaymentOrderMapper paymentOrderMapper;

  /**
   * Constructor.
   *
   * @param useCase payment use case
   * @param paymentOrderMapper payment mapper
   */
  public PaymentController(
      PaymentUseCase useCase,
      PaymentOrderMapper paymentOrderMapper) {

    this.useCase = useCase;
    this.paymentOrderMapper = paymentOrderMapper;
  }


  @Override
  public ResponseEntity<CreatePaymentOrderResponse> createPaymentOrder(
      CreatePaymentOrderRequest createPaymentOrderRequest) {

    PaymentOrder paymentOrder =
        paymentOrderMapper.createPaymentOrderRequestToPaymentOrder(
            createPaymentOrderRequest);

    PaymentOrder paymentOrderCreated =useCase.create(paymentOrder);

        CreatePaymentOrderResponse response =
        paymentOrderMapper.paymentOrderToCreatePaymentOrderResponse(
            paymentOrderCreated);

    return ResponseEntity.status(201).body(response);
  }

  @Override
  public ResponseEntity<CreatePaymentOrderResponse> getPaymentOrder(
      String paymentOrderId) {

    PaymentOrder paymentOrder=
        useCase.get(paymentOrderId);

    CreatePaymentOrderResponse response =
        paymentOrderMapper.paymentOrderToCreatePaymentOrderResponse(paymentOrder);

    return ResponseEntity.ok(response);
  }

  @Override
  public ResponseEntity<PaymentStatusResponse> getPaymentOrderStatus(
      String paymentOrderId) {

    PaymentOrder paymentOrder=
        useCase.get(paymentOrderId);

    PaymentStatusResponse response =
        paymentOrderMapper.paymentOrderToPaymentStatusResponse(paymentOrder);

    return ResponseEntity.ok(response);
  }
}