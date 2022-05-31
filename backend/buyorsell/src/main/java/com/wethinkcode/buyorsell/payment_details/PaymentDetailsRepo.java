package com.wethinkcode.buyorsell.payment_details;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentDetailsRepo extends JpaRepository<PaymentDetails, Integer> {
    
}