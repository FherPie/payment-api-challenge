package com.andres.banking.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



/**
 *  Payment Order Entity.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentOrder {

  private String paymentOrderId;

  private String externalReference;

  private String debtorAccount;

  private String creditorAccount;

  private BigDecimal amount;

  private String currency;

  private String remittanceInformation;

  private PaymentStatus status;

  private LocalDate requestedExecutionDate;

  private OffsetDateTime lastUpdate;

}