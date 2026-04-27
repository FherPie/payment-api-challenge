package com.andres.banking.adapter.in.rest.mapper;

import com.andres.banking.domain.model.PaymentOrder;
import com.andres.generated.dto.CreatePaymentOrderRequest;
import com.andres.generated.dto.CreatePaymentOrderResponse;
import com.andres.generated.dto.PaymentStatus;
import com.andres.generated.dto.PaymentStatusResponse;
import java.math.BigDecimal;
import org.springframework.stereotype.Component;

/**
 * Mapper between API DTOs and domain model PaymentOrder.
 */

@Component
public class PaymentOrderMapper {

  /**
   * Maps request DTO to domain model.
   *
   * @param req request DTO
   * @return payment order domain model
   */
  public PaymentOrder createPaymentOrderRequestToPaymentOrder(
      CreatePaymentOrderRequest req) {

    return new PaymentOrder(
        "",
        req.getExternalReference(),
        req.getDebtorAccount().getIban(),
        req.getCreditorAccount().getIban(),
        BigDecimal.valueOf(req.getInstructedAmount().getAmount()),
        req.getInstructedAmount().getCurrency(),
        req.getRemittanceInformation(),
        paymentStatusToModel(PaymentStatus.INITIATED),
        req.getRequestedExecutionDate(),
        null
    );
  }

  /**
   * Maps domain model to create response DTO.
   *
   * @param pay payment order
   * @return response DTO
   */
  public CreatePaymentOrderResponse paymentOrderToCreatePaymentOrderResponse(
      PaymentOrder pay) {

    CreatePaymentOrderResponse response =
        new CreatePaymentOrderResponse();

    response.setPaymentOrderId(pay.getPaymentOrderId());
    response.setStatus(paymentStatusToDto(pay.getStatus()));

    return response;
  }

  /**
   * Maps domain model to payment status response DTO.
   *
   * @param pay payment order
   * @return status response DTO
   */
  public PaymentStatusResponse paymentOrderToPaymentStatusResponse(
      PaymentOrder pay) {

    PaymentStatusResponse response =
        new PaymentStatusResponse();

    response.setPaymentOrderId(pay.getPaymentOrderId());
    response.setStatus(paymentStatusToDto(pay.getStatus()));
    response.setLastUpdate(pay.getLastUpdate());

    return response;
  }

  /**
   * Maps domain status to DTO status.
   *
   * @param status domain status
   * @return dto status
   */
  private PaymentStatus paymentStatusToDto(
      com.andres.banking.domain.model.PaymentStatus status) {

    return switch (status) {
      case INITIATED -> PaymentStatus.INITIATED;
      case PROCESSING -> PaymentStatus.PROCESSING;
      case COMPLETED -> PaymentStatus.COMPLETED;
      case REJECTED -> PaymentStatus.REJECTED;
    };
  }

  /**
   * Maps DTO status to domain status.
   *
   * @param status dto status
   * @return domain status
   */
  private com.andres.banking.domain.model.PaymentStatus paymentStatusToModel(
      PaymentStatus status) {

    return switch (status) {
      case INITIATED -> com.andres.banking.domain.model.PaymentStatus.INITIATED;
      case VALIDATED -> com.andres.banking.domain.model.PaymentStatus.INITIATED;
      case PROCESSING -> com.andres.banking.domain.model.PaymentStatus.PROCESSING;
      case COMPLETED -> com.andres.banking.domain.model.PaymentStatus.COMPLETED;
      case REJECTED -> com.andres.banking.domain.model.PaymentStatus.REJECTED;
    };
  }
}