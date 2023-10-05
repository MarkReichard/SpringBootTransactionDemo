package com.idatadc.springboottransactiondemo.service.impl;

import com.idatadc.springboottransactiondemo.dto.OrderRequest;
import com.idatadc.springboottransactiondemo.dto.OrderResponse;
import com.idatadc.springboottransactiondemo.entity.Order;
import com.idatadc.springboottransactiondemo.entity.Payment;
import com.idatadc.springboottransactiondemo.exception.PaymentException;
import com.idatadc.springboottransactiondemo.repository.OrderRepository;
import com.idatadc.springboottransactiondemo.repository.PaymentRepository;
import com.idatadc.springboottransactiondemo.service.OrderService;
import jakarta.transaction.TransactionScoped;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private PaymentRepository paymentRepository;

    public OrderServiceImpl(OrderRepository orderRepository, PaymentRepository paymentRepository) {
        this.orderRepository = orderRepository;
        this.paymentRepository = paymentRepository;
    }

    @Override
    public OrderResponse placeOrder(OrderRequest orderRequest) {
        Order order = orderRequest.getOrder();
        order.setStatus("pending");
        order.setOrderTrackingNumber(UUID.randomUUID().toString());
        orderRepository.save(order);

        Payment payment = orderRequest.getPayment();
        if(!payment.getType().equals("DEBIT")){
            throw new PaymentException("Payment card type not supported");
        }
        payment.setOrderId(order.getId());
        paymentRepository.save(payment);
        OrderResponse orderResponse =  new OrderResponse();
        orderResponse.setOrderTrackingNumber(order.getOrderTrackingNumber());
        orderResponse.setStatus(order.getStatus());
        orderResponse.setMessage("Order placed successfully");
        return orderResponse;

    }
}
