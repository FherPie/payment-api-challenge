package com.andres.banking.unit.application;


import com.andres.banking.domain.service.PaymentService;
import com.andres.banking.application.exception.PaymentNotFoundException;
import com.andres.banking.application.port.out.PaymentRepository;
import com.andres.banking.domain.model.PaymentOrder;
import com.andres.banking.domain.model.PaymentStatus;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit Test of PaymentService.
 */
public class PaymentServiceTest {


    private final PaymentRepository repo =
        mock(PaymentRepository.class);

    private final PaymentService service =
            new PaymentService(repo);


    @Test
    void shouldCreatePaymentOrder(){
        PaymentOrder order = newOrder();
        when(repo.save(order)).thenReturn(order);
        PaymentOrder created= service.create(order);

        verify(repo, times(1))
            .save(order);
        assertNotNull(created);
        assertEquals("EXT-1", created.getExternalReference());
        assertEquals(PaymentStatus.INITIATED, created.getStatus());
    }


    @Test
    void shouldGetPaymentOrder(){
        PaymentOrder order = newOrder();
        when(repo.save(order)).thenReturn(order);
        PaymentOrder created= service.create(order);

        when(repo.findById(created.getPaymentOrderId())).thenReturn(Optional.of(created));

        var found=service.get(created.getPaymentOrderId());

        verify(repo, times(1))
            .findById(created.getPaymentOrderId());

        assertNotNull(found);
        assertEquals(created.getPaymentOrderId(), found.getPaymentOrderId());
        assertEquals("EXT-1", found.getExternalReference());
    }


    @Test
    void shouldGetPaymentStatus(){
        PaymentOrder order = newOrder();
        when(repo.save(order)).thenReturn(order);
        PaymentOrder created= service.create(order);

        when(repo.findById(created.getPaymentOrderId())).thenReturn(Optional.of(created));

        var found=service.getStatus(created.getPaymentOrderId());

        verify(repo, times(1))
            .findById(created.getPaymentOrderId());

        assertNotNull(found);
        assertEquals(created.getPaymentOrderId(), found.getPaymentOrderId());
        assertEquals("EXT-1", found.getExternalReference());
    }

    @Test
    void shouldThrowWhenPaymentOrderNotFound(){
        PaymentOrder order = newOrder();
        when(repo.save(order)).thenReturn(order);

        PaymentOrder pay= service.create(order);

        pay.setPaymentOrderId("PO-999");

        assertThrows(
            PaymentNotFoundException.class,
            () -> service.get("PO-999"));
    }


    private PaymentOrder newOrder() {
        return new PaymentOrder("",
                "EXT-1",
                "EC12DEBTOR",
                "EC98CREDITOR",
                new BigDecimal("150.75"),"USD",
                "Factura 001-123",
                PaymentStatus.INITIATED, LocalDate.of(2025,10,31), OffsetDateTime.now());
    }

}
