package com.idatadc.springboottransactiondemo.repository;

import com.idatadc.springboottransactiondemo.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
