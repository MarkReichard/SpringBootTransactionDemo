package com.idatadc.springboottransactiondemo.service;

import com.idatadc.springboottransactiondemo.dto.OrderRequest;
import com.idatadc.springboottransactiondemo.dto.OrderResponse;

public interface OrderService {
    OrderResponse placeOrder(OrderRequest orderRequest);
}
