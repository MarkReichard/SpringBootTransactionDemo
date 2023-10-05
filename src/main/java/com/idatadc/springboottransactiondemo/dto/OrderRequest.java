package com.idatadc.springboottransactiondemo.dto;

import com.idatadc.springboottransactiondemo.entity.Order;
import com.idatadc.springboottransactiondemo.entity.Payment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {
    private Order order;
    private Payment payment;
}
