package com.idatadc.springboottransactiondemo.repository;

import com.idatadc.springboottransactiondemo.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
