package com.idatadc.springboottransactiondemo;

import com.idatadc.springboottransactiondemo.dto.OrderRequest;
import com.idatadc.springboottransactiondemo.dto.OrderResponse;
import com.idatadc.springboottransactiondemo.entity.Order;
import com.idatadc.springboottransactiondemo.entity.Payment;
import com.idatadc.springboottransactiondemo.exception.PaymentException;
import com.idatadc.springboottransactiondemo.repository.OrderRepository;
import com.idatadc.springboottransactiondemo.repository.PaymentRepository;
import com.idatadc.springboottransactiondemo.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OrderServiceImplTest {

    @InjectMocks
    private OrderServiceImpl orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private PaymentRepository paymentRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void placeOrder_ValidDebitPayment_ReturnsOrderResponse() {
        OrderRequest orderRequest = new OrderRequest();
        Order order = new Order();
        orderRequest.setOrder(order);

        Payment payment = new Payment();
        payment.setType("DEBIT");
        orderRequest.setPayment(payment);

        when(orderRepository.save(any(Order.class))).thenReturn(order);
        when(paymentRepository.save(any(Payment.class))).thenReturn(payment);

        OrderResponse response = orderService.placeOrder(orderRequest);

        assertNotNull(response);
        assertEquals("pending", response.getStatus());
        assertNotNull(response.getOrderTrackingNumber());
        assertEquals("Order placed successfully", response.getMessage());
    }

    @Test
    void placeOrder_InvalidPaymentType_ThrowsPaymentException() {
        OrderRequest orderRequest = new OrderRequest();
        Order order = new Order();
        orderRequest.setOrder(order);

        Payment payment = new Payment();
        payment.setType("CREDIT");
        orderRequest.setPayment(payment);

        assertThrows(PaymentException.class, () -> orderService.placeOrder(orderRequest));
    }
}
