package com.andres.banking.integration.adapter.in.rest;


import com.andres.banking.domain.service.PaymentService;
import com.andres.banking.domain.model.PaymentOrder;
import com.andres.banking.domain.model.PaymentStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Integration Test of Payment Controller.
 */
@SpringBootTest
@AutoConfigureMockMvc
public class PaymentControllerIT {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private PaymentService service;


    @Test
    void shouldCreatePaymentOrder() throws Exception {

        String json = """
                {
                  "externalReference": "EXT-1",
                  "debtorAccount": { "iban": "EC12DEBTOR" },
                  "creditorAccount": { "iban": "EC98CREDITOR" },
                  "instructedAmount": { "amount": 150.75, "currency": "USD" },
                  "remittanceInformation": "Factura 001-123",
                  "requestedExecutionDate": "2025-10-31"
                } 
      """;

        mockMvc.perform(post("/payment-initiation/payment-orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());
    }



    @Test
    void shouldRetrievePaymentOrder() throws Exception {

        PaymentOrder order = newOrder();
        PaymentOrder pay= service.create(order);


        mockMvc.perform(
                        get("/payment-initiation/payment-orders/"+pay.getPaymentOrderId())
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.paymentOrderId").value(pay.getPaymentOrderId()))
                .andExpect(jsonPath("$.status").value("INITIATED"));
    }



    @Test
    void shouldRetrievePaymentOrderStatus() throws Exception {

        PaymentOrder order =newOrder();
        PaymentOrder pay= service.create(order);

        mockMvc.perform(
                        get("/payment-initiation/payment-orders/"+pay.getPaymentOrderId()+"/status")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.paymentOrderId").value(pay.getPaymentOrderId()))
                .andExpect(jsonPath("$.status").value("INITIATED"))
                .andExpect(jsonPath("$.lastUpdate").exists()).andDo(print());
    }


    @Test
    void shouldReturnNotFoundWhenPaymentDoesNotExist()
        throws Exception {

        mockMvc.perform(
                get("/payment-initiation/payment-orders/PO-999"))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.title")
                .value("Payment Order Not Found"))
            .andExpect(jsonPath("$.status")
                .value(404)).andDo(print());
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
