package com.andres.banking.unit.adapter.in.rest.mapper;


import com.andres.banking.adapter.in.rest.mapper.PaymentOrderMapper;
import com.andres.banking.domain.model.PaymentOrder;
import com.andres.generated.dto.Account;
import com.andres.generated.dto.CreatePaymentOrderRequest;
import com.andres.generated.dto.InstructedAmount;
import com.andres.generated.dto.PaymentStatus;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test of PaymentOrderMapper
 */
public class PaymentOrderMapperTest {

    private final PaymentOrderMapper mapper = new PaymentOrderMapper();

    @Test
    void shouldMapDtotoModel(){
        CreatePaymentOrderRequest req= new CreatePaymentOrderRequest();
        req.externalReference("EXT-1");
        Account deb= new Account();
        deb.setIban("EC12DEBTOR");
        Account cred= new Account();
        cred.setIban("EC98CREDITOR");
        InstructedAmount amount=new InstructedAmount();
        amount.setAmount(150.75);
        amount.setCurrency("USD");
        req.setDebtorAccount(deb);
        req.setCreditorAccount(cred);
        req.setInstructedAmount(amount);
        req.setRemittanceInformation("Factura 001-123");
        req.setRequestedExecutionDate(LocalDate.of(2025,10,31));
        var paymentorder= mapper.createPaymentOrderRequestToPaymentOrder(req);
        assertEquals("EXT-1", paymentorder.getExternalReference());
        assertEquals("EC12DEBTOR", paymentorder.getDebtorAccount());
        assertEquals("EC98CREDITOR", paymentorder.getCreditorAccount());
        assertEquals(new BigDecimal("150.75"), paymentorder.getAmount());
        assertEquals("USD", paymentorder.getCurrency());
        assertEquals("Factura 001-123", paymentorder.getRemittanceInformation());
        assertEquals(LocalDate.of(2025,10,31), paymentorder.getRequestedExecutionDate());
        assertNull(paymentorder.getLastUpdate());

    }



    @Test
    void shouldMapPaymentOrdertoCreatePaymentOrderResponse(){
        PaymentOrder paymentOrder=newOrder();
        var createPaymentOrderResponse =
            mapper.paymentOrderToCreatePaymentOrderResponse(newOrder());
        assertEquals(PaymentStatus.INITIATED, createPaymentOrderResponse.getStatus());
        assertEquals(createPaymentOrderResponse.getPaymentOrderId(),paymentOrder.getPaymentOrderId() );
    }


    @Test
    void shouldMapPaymentOrdertoPaymentStatusResponse(){
        PaymentOrder paymentOrder=newOrder();
        var paymentStatusResponse = mapper.paymentOrderToPaymentStatusResponse(newOrder());
        assertEquals(paymentStatusResponse.getPaymentOrderId(),paymentOrder.getPaymentOrderId() );
        assertEquals(PaymentStatus.INITIATED, paymentStatusResponse.getStatus());
        assertNotNull(paymentStatusResponse.getLastUpdate());
    }


    private PaymentOrder newOrder() {
        return new PaymentOrder("",
                "EXT-1",
                "EC12DEBTOR",
                "EC98CREDITOR",
                new BigDecimal("150.75"),"USD",
                "Factura 001-123",
                com.andres.banking.domain.model.PaymentStatus.INITIATED, LocalDate.of(2025,10,31), OffsetDateTime.now());
    }
}
