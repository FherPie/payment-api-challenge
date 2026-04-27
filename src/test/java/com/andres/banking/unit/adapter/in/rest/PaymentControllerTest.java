package com.andres.banking.unit.adapter.in.rest;

import com.andres.banking.adapter.in.rest.PaymentController;
import com.andres.banking.adapter.in.rest.mapper.PaymentOrderMapper;
import com.andres.banking.application.port.in.PaymentUseCase;
import com.andres.banking.domain.model.PaymentOrder;
import com.andres.generated.dto.CreatePaymentOrderRequest;
import com.andres.generated.dto.CreatePaymentOrderResponse;
import com.andres.generated.dto.PaymentStatusResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Unit Test of Payment Controller.
 */
class PaymentControllerTest {

  @Mock
  private PaymentUseCase useCase;

  @Mock
  private PaymentOrderMapper mapper;

  private PaymentController controller;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    controller = new PaymentController(useCase, mapper);
  }

  @Test
  void shouldCreatePaymentOrder() {
    CreatePaymentOrderRequest request =
        new CreatePaymentOrderRequest();

    PaymentOrder order = new PaymentOrder();
    order.setExternalReference("ABC");

    CreatePaymentOrderResponse response =
        new CreatePaymentOrderResponse();

    when(mapper.createPaymentOrderRequestToPaymentOrder(request))
        .thenReturn(order);

    when(useCase.create(order))
        .thenReturn(order);

    when(mapper.paymentOrderToCreatePaymentOrderResponse(order))
        .thenReturn(response);

    ResponseEntity<CreatePaymentOrderResponse> result =
        controller.createPaymentOrder(request);


    assertEquals(HttpStatus.CREATED, result.getStatusCode());

    assertEquals(201, result.getStatusCode().value());
    assertEquals(response, result.getBody());
    assertEquals(response.getPaymentOrderId(), Objects.requireNonNull(result.getBody()).getPaymentOrderId());

    verify(mapper, times(1))
        .createPaymentOrderRequestToPaymentOrder(request);

    verify(useCase, times(1))
        .create(order);

    verify(mapper, times(1))
        .paymentOrderToCreatePaymentOrderResponse(order);

    verifyNoMoreInteractions(useCase, mapper);

  }


  @Test
  void shouldGetPaymentOrder() {
    PaymentOrder order = new PaymentOrder();

    CreatePaymentOrderResponse response =
        new CreatePaymentOrderResponse();

    when(useCase.get("PO-1"))
        .thenReturn(order);

    when(mapper.paymentOrderToCreatePaymentOrderResponse(order))
        .thenReturn(response);

    ResponseEntity<CreatePaymentOrderResponse> result =
        controller.getPaymentOrder("PO-1");


    verify(useCase, times(1))
        .get("PO-1");

    verify(mapper, times(1))
        .paymentOrderToCreatePaymentOrderResponse(order);

    assertEquals(200, result.getStatusCode().value());
    assertEquals(response, result.getBody());


  }


  @Test
  void shouldGetPaymentOrderStatus() {
    PaymentOrder order = new PaymentOrder();

    PaymentStatusResponse response =
        new PaymentStatusResponse();

    when(useCase.get("PO-1"))
        .thenReturn(order);

    when(mapper.paymentOrderToPaymentStatusResponse(order))
        .thenReturn(response);

    ResponseEntity<PaymentStatusResponse> result =
        controller.getPaymentOrderStatus("PO-1");

    verify(useCase, times(1))
        .get("PO-1");

    verify(mapper, times(1))
        .paymentOrderToPaymentStatusResponse(order);

    assertEquals(200, result.getStatusCode().value());
    assertEquals(response, result.getBody());
  }
}